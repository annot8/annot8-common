/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.runnable;

import io.annot8.queues.ItemQueue;
import java.util.Arrays;
import java.util.Collection;

import io.annot8.common.pipelines.PipelineBuilder;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;

public interface RunnablePipelineBuilder extends PipelineBuilder {

  default RunnablePipelineBuilder addSource(final Source source, final Settings... settings) {
    addSource(source, Arrays.asList(settings));
    return this;
  }

  RunnablePipelineBuilder addSource(final Source source, final Collection<Settings> settings);

  default RunnablePipelineBuilder addResource(
      final String id, final Resource resource, final Settings... settings) {
    return addResource(id, resource, Arrays.asList(settings));
  }

  default RunnablePipelineBuilder addProcessor(
      final Processor processor, final Settings... settings) {
    return addProcessor(processor, Arrays.asList(settings));
  }

  RunnablePipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> settings);

  RunnablePipelineBuilder addProcessor(
      final Processor processor, final Collection<Settings> settings);

  RunnablePipelineBuilder withQueue(final ItemQueue queue);

  RunnablePipeline build() throws IncompleteException;
}
