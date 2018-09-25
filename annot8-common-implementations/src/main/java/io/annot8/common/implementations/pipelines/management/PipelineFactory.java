/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.management;

import io.annot8.common.implementations.pipelines.Pipeline;
import io.annot8.common.implementations.pipelines.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;

public interface PipelineFactory {

  Pipeline create(PipelineConfiguration configuration) throws IncompleteException;
}
