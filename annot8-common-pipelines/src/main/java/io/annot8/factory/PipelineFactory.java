/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.factory;

import io.annot8.factory.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.pipelines.elements.Pipeline;

public interface PipelineFactory {

  Pipeline create(PipelineConfiguration configuration) throws IncompleteException;
}
