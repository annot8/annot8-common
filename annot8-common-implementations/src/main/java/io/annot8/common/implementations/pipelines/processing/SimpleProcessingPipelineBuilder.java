/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.processing;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleProcessingPipelineBuilder extends AbstractProcessingPipelineBuilder
    implements ProcessingPipelineBuilder {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SimpleProcessingPipelineBuilder.class);

  @Override
  public ProcessingPipeline build() {
    return new SimpleProcessingPipeline(
        getResourcesHolder(), getProcessorHolder(), Optional.ofNullable(getQueue()));
  }
}
