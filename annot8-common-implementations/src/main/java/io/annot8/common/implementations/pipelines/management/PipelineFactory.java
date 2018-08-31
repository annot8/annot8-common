package io.annot8.common.implementations.pipelines.management;

import io.annot8.common.implementations.pipelines.Pipeline;
import io.annot8.common.implementations.pipelines.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;

public interface PipelineFactory {

  Pipeline create(PipelineConfiguration configuration) throws IncompleteException;

}
