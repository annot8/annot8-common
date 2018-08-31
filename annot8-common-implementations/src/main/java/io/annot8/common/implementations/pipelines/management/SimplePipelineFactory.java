package io.annot8.common.implementations.pipelines.management;

import io.annot8.common.implementations.pipelines.Pipeline;
import io.annot8.common.implementations.pipelines.PipelineBuilder;
import io.annot8.common.implementations.pipelines.configuration.ComponentConfiguration;
import io.annot8.common.implementations.pipelines.configuration.PipelineConfiguration;
import io.annot8.common.implementations.pipelines.configuration.TypedComponentConfiguration;
import io.annot8.common.implementations.pipelines.management.PipelineMetadata.Builder;
import io.annot8.common.implementations.registries.Annot8ComponentRegistry;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;
import java.util.Collection;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePipelineFactory implements PipelineFactory {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SimplePipelineFactory.class);

  private final Supplier<PipelineMetadata.Builder> metadataFactory;
  private final Supplier<PipelineBuilder> builderFactory;
  private final Annot8ComponentRegistry componentRegistry;

  public SimplePipelineFactory(
      Supplier<Builder> metadataFactory, Supplier<PipelineBuilder> builderFactory, Annot8ComponentRegistry componentRegistry) {
    this.metadataFactory = metadataFactory;
    this.builderFactory = builderFactory;
    this.componentRegistry = componentRegistry;
  }

  @Override
  public PipelineMetadata create(PipelineConfiguration pipelineConfiguration)
      throws IncompleteException {
      return metadataFactory.get()
          .withConfiguration(pipelineConfiguration)
          .withId(UUID.randomUUID().toString())
          .withPipeline(createPipeline(pipelineConfiguration))
          .build();

  }

  private Pipeline createPipeline(PipelineConfiguration configuration) throws IncompleteException {
    PipelineBuilder pipelineBuilder = builderFactory.get();


    configuration.getSources().stream()
        .forEach(s -> addComponentToBuilder(Source.class, s, (i, c) -> pipelineBuilder.addDataSource(i, c)));

    configuration.getSources().stream()
        .forEach(s -> addComponentToBuilder(
            Processor.class, s, (i, c) -> pipelineBuilder.addProcessor(i, c)));

    configuration.getSources().stream()
        .forEach(s -> addComponentToBuilder(Resource.class, s, (i, c) -> pipelineBuilder.addResource(s.getName(), i, c)));

    return pipelineBuilder.build();
  }

  private <T extends Annot8Component> void addComponentToBuilder(Class<T> clazz, ComponentConfiguration config, BiConsumer<T, Collection<Settings>> consumer) {

    try {
      TypedComponentConfiguration<T> tcc = validateComponent(
          config, clazz);

      T s = createInstance(tcc.getComponentClass());
      consumer.accept(s, tcc.getSettings());

    } catch (Annot8Exception e) {
      LOGGER.warn(e.getMessage());
    }

  }

  private <T extends Annot8Component> TypedComponentConfiguration<T> validateComponent(
      ComponentConfiguration config, Class<T> componentType) throws Annot8Exception {
    try {
      Class<? extends T> componentClass = componentClass =
          componentRegistry.getComponent(config.getComponent(), componentType).get();
      return new TypedComponentConfiguration<T>(componentClass, config.getSettings());
    } catch (Exception e) {
      throw new Annot8Exception("Could not find class implementation for component name "
          + config.getComponent(), e);
    }
  }

  private <T> T createInstance(Class<T> clazz) throws Annot8Exception {
    try {
      return clazz.getConstructor().newInstance();
    } catch (Exception e) {
      throw new Annot8Exception("Could not create instance of component "
          + clazz.getName(), e);
    }
  }

}
