/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.runnable;

import io.annot8.common.pipelines.queues.ItemQueue;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.core.exceptions.IncompleteException;

public class SimpleRunnablePipelineBuilder extends AbstractRunnablePipelineBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRunnablePipelineBuilder.class);

  @Override
  public RunnablePipeline build() throws IncompleteException {
    ItemQueue queue = getQueue();

    return new SimpleRunnablePipeline(
        getResourcesHolder(), getSourceHolder(), getProcessorHolder(), Optional.ofNullable(queue));
  }
}
