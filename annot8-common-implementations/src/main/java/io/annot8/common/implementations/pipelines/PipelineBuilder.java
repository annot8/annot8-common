package io.annot8.common.implementations.pipelines;

import io.annot8.common.implementations.factories.ContentBuilderFactory;
import io.annot8.common.implementations.registries.ContentBuilderFactoryRegistry;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.data.Content;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;
import java.util.Arrays;
import java.util.Collection;

public interface PipelineBuilder {

  <D, C extends Content<D>, I extends C> PipelineBuilder addContentBuilder(Class<C> contentClass,
      ContentBuilderFactory<D, I> factory);

  PipelineBuilder withItemQueue(ItemQueue itemQueue);

  PipelineBuilder withItemFactory(ItemFactory itemFactory);

  PipelineBuilder withContentBuilderFactory(ContentBuilderFactoryRegistry registry);

  default PipelineBuilder addResource(final String id, final Resource resource, final Settings... settings)  {
    addResource(id, resource, Arrays.asList(settings));
    return this;
  }

  default PipelineBuilder addDataSource(final Source source, final Settings... settings) {
    addDataSource(source, Arrays.asList(settings));
    return this;
  }

  default PipelineBuilder addProcessor(final Processor processor, final Settings... settings) {
    addProcessor(processor, Arrays.asList(settings));
    return this;
  }

  PipelineBuilder addResource(final String id, final Resource resource, final Collection<Settings> settings) ;

  PipelineBuilder addDataSource(final Source source, final Collection<Settings> settings);

  PipelineBuilder addProcessor(final Processor processor, final Collection<Settings> settings);

  Pipeline build() throws IncompleteException;
}
