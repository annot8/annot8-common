package io.annot8.common.implementations.pipelines.management;

import io.annot8.common.implementations.pipelines.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;

public interface PipelineFactory {

  PipelineMetadata create(PipelineConfiguration configuration) throws IncompleteException;

}
