/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.creation;

import io.annot8.common.pipelines.creation.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;

public interface PipelineFactory {

  RunnablePipeline create(PipelineConfiguration configuration) throws IncompleteException;
}
