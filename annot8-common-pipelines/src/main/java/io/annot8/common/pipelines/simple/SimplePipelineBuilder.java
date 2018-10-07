/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.pipelines.base.AbstractPipelineBuilder;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.queues.ItemQueue;
import io.annot8.core.exceptions.IncompleteException;

public class SimplePipelineBuilder extends AbstractPipelineBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipelineBuilder.class);

  @Override
  public Pipeline build() throws IncompleteException {
    String name = getName();
    ItemQueue queue = getQueue();

    List<Pipe> pipes = getPipes().get(DEFAULT_PIPE);

    Objects.requireNonNull(queue);
    Objects.requireNonNull(name);

    return new SimplePipeline(
        name, getResourcesHolder(), getSourceHolder(), pipes, Optional.ofNullable(queue));
  }
}
