/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import java.util.Arrays;
import java.util.Collection;

import io.annot8.common.pipelines.queues.ItemQueue;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;

public interface PipelineBuilder {
  PipelineBuilder withName(String name);

  default PipelineBuilder addResource(
      final String id, final Resource resource, final Settings... settings) {
    return addResource(id, resource, Arrays.asList(settings));
  }

  PipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> settings);

  default PipelineBuilder addSource(final Source source, final Settings... settings) {
    addSource(source, Arrays.asList(settings));
    return this;
  }

  PipelineBuilder addSource(final Source source, final Collection<Settings> settings);

  PipelineBuilder addPipe(final PipeBuilder pipe) throws IncompleteException;

  PipelineBuilder addPipe(String key, final PipeBuilder pipe) throws IncompleteException;

  PipelineBuilder addPipe(Pipe pipe);

  PipelineBuilder addPipe(String key, Pipe pipe);

  PipelineBuilder addBranch(final Branch branch, String... keys);

  PipelineBuilder addMerge(final Merge merge, String... keys);

  PipelineBuilder withQueue(ItemQueue queue);

  Pipeline build() throws IncompleteException;
}