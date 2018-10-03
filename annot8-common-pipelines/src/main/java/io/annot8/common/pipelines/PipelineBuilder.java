/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines;

import io.annot8.common.pipelines.queues.ItemQueue;
import java.util.Arrays;
import java.util.Collection;

import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;

public interface PipelineBuilder {

  default PipelineBuilder addResource(
      final String id, final Resource resource, final Settings... settings) {
    return addResource(id, resource, Arrays.asList(settings));
  }

  default PipelineBuilder addProcessor(final Processor processor, final Settings... settings) {
    return addProcessor(processor, Arrays.asList(settings));
  }

  PipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> settings);

  PipelineBuilder addProcessor(final Processor processor, final Collection<Settings> settings);

  PipelineBuilder withQueue(final ItemQueue queue);

  Pipeline build() throws IncompleteException;
}
