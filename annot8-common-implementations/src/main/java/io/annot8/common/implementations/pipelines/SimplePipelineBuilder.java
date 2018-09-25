package io.annot8.common.implementations.pipelines;


import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.implementations.factories.ItemCreator;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePipelineBuilder implements PipelineBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipeline.class);

  protected ItemQueue itemQueue;
  protected ItemCreator itemCreator;

  // Use a linked hash map so the addition order = configuration order
  private final Map<Source, Collection<Settings>> sourcesToConfiguration = new LinkedHashMap<>();
  private final Map<Processor, Collection<Settings>> processorToConfiguration = new LinkedHashMap<>();
  private final Map<Resource, Collection<Settings>> resourcesToConfiguration = new LinkedHashMap<>();
  private final Map<Resource, String> resourcesToId = new HashMap<>();

  public PipelineBuilder withItemQueue(ItemQueue itemQueue) {
    this.itemQueue = itemQueue;
    return this;
  }

  public PipelineBuilder withItemCreator(ItemCreator itemCreator) {
    this.itemCreator = itemCreator;
    return this;
  }

  public PipelineBuilder addResource(final String id, final Resource resource, final Collection<Settings> configuration) {
    resourcesToConfiguration.put(resource, nonNullCollection(configuration));
    resourcesToId.put(resource, id);
    return this;
  }

  public PipelineBuilder addSource(final Source source, final Collection<Settings> configuration) {
    sourcesToConfiguration.put(source, nonNullCollection(configuration));
    return this;
  }



  public PipelineBuilder addProcessor(final Processor processor, final Collection<Settings> configuration) {
    processorToConfiguration.put(processor, nonNullCollection(configuration));
    return this;
  }

  public Pipeline build() throws IncompleteException {

    if(itemCreator == null) {
      throw new IncompleteException("No item creator specified");
    }

    if(itemQueue == null) {;
      LOGGER.warn("No item queue specified, (using SimpleItemQueue as an basic implementation)");
      this.itemQueue = new SimpleItemQueue();
    }

    Map<String, Resource> configuredResources = new HashMap<>();

    resourcesToConfiguration.forEach((resource, settings) -> {

      if (configureComponent(configuredResources, resource, settings)) {
        String id = resourcesToId.get(resource);
        configuredResources.put(id, resource);
      }
    });

    List<Source> configuredSources = configureAllComponents(configuredResources,
        sourcesToConfiguration);
    List<Processor> configurePipelines = configureAllComponents(configuredResources,
        processorToConfiguration);

    return new SimplePipeline(itemCreator, itemQueue, configuredResources, configuredSources,
        configurePipelines);
  }

  protected  <T extends Annot8Component> List<T> configureAllComponents(Map<String, Resource> configuredResources, Map<T, Collection<Settings>> componentToConfiguration) {

    return componentToConfiguration.entrySet().stream()
        .filter(e -> configureComponent(configuredResources, e.getKey(), e.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  protected  boolean configureComponent(Map<String, Resource> configuredResources, final Annot8Component component,
      final Collection<Settings> configuration) {

    // TODO: COmpletely ignore capabilties here.. we could check for resources etc

    try {
      final SimpleContext context = new SimpleContext(configuration,
          configuredResources);
      component.configure(context);
      return true;
    } catch (final Exception e) {
      LOGGER.error("Failed to configure component {}",component.getClass().getName(),e);
    }
    return false;
  }

  private Collection<Settings> nonNullCollection(Collection<Settings> configuration) {
    return configuration == null ? Collections.emptySet() : configuration;
  }
}