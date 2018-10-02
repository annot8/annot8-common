/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.processing;

import java.util.Arrays;
import java.util.Collection;

import io.annot8.common.implementations.pipelines.PipelineBuilder;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;

public interface ProcessingPipelineBuilder extends PipelineBuilder {

  default ProcessingPipelineBuilder addResource(
      final String id, final Resource resource, final Settings... settings) {
    return addResource(id, resource, Arrays.asList(settings));
  }

  default ProcessingPipelineBuilder addProcessor(
      final Processor processor, final Settings... settings) {
    return addProcessor(processor, Arrays.asList(settings));
  }

  ProcessingPipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> settings);

  ProcessingPipelineBuilder addProcessor(
      final Processor processor, final Collection<Settings> settings);

  ProcessingPipeline build() throws IncompleteException;
}
