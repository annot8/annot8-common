package io.annot8.common.implementations.pipelines;


import io.annot8.common.implementations.context.SimpleContext;
import io.annot8.common.implementations.factories.ContentBuilderFactory;
import io.annot8.common.implementations.registries.ContentBuilderFactoryRegistry;
import io.annot8.common.implementations.registries.SimpleContentBuilderFactoryRegistry;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.data.Content;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
  protected ContentBuilderFactoryRegistry contentBuilderFactoryRegistry;
  protected ItemFactory itemFactory;

  // Use a linked hash map so the addition order = configuration order
  private final Map<Source, Collection<Settings>> sourcesToConfiguration = new LinkedHashMap<>();
  private final Map<Processor, Collection<Settings>> processorToConfiguration = new LinkedHashMap<>();
  private final Map<Resource, Collection<Settings>> resourcesToConfiguration = new LinkedHashMap<>();
  private final Map<Resource, String> resourcesToId = new HashMap<>();
  private final List<ContentClassWithBuilder<?,?,?>> contentBuilders = new ArrayList<>();


  public <D, C extends Content<D>, I extends C> PipelineBuilder addContentBuilder(Class<C> contentClass,
      ContentBuilderFactory<D, I> factory) {
    contentBuilders.add(new ContentClassWithBuilder<>(contentClass, factory));
    return this;
  }

  public PipelineBuilder withItemQueue(ItemQueue itemQueue) {
    this.itemQueue = itemQueue;
    return this;
  }

  public PipelineBuilder withItemFactory(ItemFactory itemFactory) {
    this.itemFactory = itemFactory;
    return this;
  }


  public PipelineBuilder withContentBuilderFactory(ContentBuilderFactoryRegistry registry) {
    this.contentBuilderFactoryRegistry = registry;
    return this;
  }

  public PipelineBuilder addResource(final String id, final Resource resource, final Settings... configuration) {
    resourcesToConfiguration.put(resource, Arrays.asList(configuration));
    resourcesToId.put(resource, id);
    return this;
  }

  public PipelineBuilder addDataSource(final Source source, final Settings... configuration) {
    sourcesToConfiguration.put(source, Arrays.asList(configuration));
    return this;
  }

  public PipelineBuilder addProcessor(final Processor processor, final Settings... configuration) {
    processorToConfiguration.put(processor, Arrays.asList(configuration));
    return this;
  }

  public Pipeline build() throws IncompleteException {

    if(itemFactory == null) {
      throw new IncompleteException("No item factory specified");
    }

    if(contentBuilderFactoryRegistry != null) {
      contentBuilderFactoryRegistry =  new SimpleContentBuilderFactoryRegistry();
      // We do not warn about this as the implementation has no really drawbacks
    }


    if(itemQueue == null) {;
      LOGGER.warn("No item queue specified, you no child items will be created (use SimpleItemQueue as an basic implemntation)");
    }


    contentBuilders.forEach(e ->
        e.registerWith(contentBuilderFactoryRegistry)
    );

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

    return new SimplePipeline(itemFactory, itemQueue, configuredResources, configuredSources,
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

  private static class ContentClassWithBuilder<D, C extends Content<D>, I extends C> {
    private final Class<C> contentClass;
    private final ContentBuilderFactory<D, I> factory;


    private ContentClassWithBuilder(Class<C> contentClass,
        ContentBuilderFactory<D, I> factory) {
      this.contentClass = contentClass;
      this.factory = factory;
    }

    public Class<C> getContentClass() {
      return contentClass;
    }

    public ContentBuilderFactory<D, I> getFactory() {
      return factory;
    }

    public void registerWith(ContentBuilderFactoryRegistry contentBuilderFactoryRegistry) {
      contentBuilderFactoryRegistry.register(getContentClass(), getFactory());
    }
  }
}