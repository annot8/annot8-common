package io.annot8.common.implementations.pipelines.management;

import io.annot8.common.implementations.pipelines.Pipeline;
import io.annot8.common.implementations.pipelines.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.helpers.builders.WithIdBuilder;

public interface PipelineMetadata {

  String getId();

  PipelineConfiguration getPipelineConfiguration();

  Pipeline getPipeline();

  interface Builder extends WithIdBuilder<PipelineMetadata.Builder> {

    PipelineMetadata.Builder withPipeline(Pipeline pipeline);

    PipelineMetadata.Builder withConfiguration(PipelineConfiguration pipelineConfiguration);

    PipelineMetadata build() throws IncompleteException;

  }

}
