package io.annot8.common.implementations.pipelines.management;

import io.annot8.common.implementations.pipelines.Pipeline;
import io.annot8.common.implementations.pipelines.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;
import java.util.UUID;

public class SimplePipelineMetadata implements PipelineMetadata {

  private final String id;

  private final Pipeline pipeline;

  private final PipelineConfiguration pipelineConfiguration;

  public SimplePipelineMetadata(String id,
      PipelineConfiguration pipelineConfiguration, Pipeline pipeline) {
    this.id = id;
    this.pipeline = pipeline;
    this.pipelineConfiguration = pipelineConfiguration;
  }

  public PipelineConfiguration getPipelineConfiguration() {
    return pipelineConfiguration;
  }

  public String getId() {
    return id;
  }

  @Override
  public Pipeline getPipeline() {
    return pipeline;
  }

  public static class Builder implements PipelineMetadata.Builder {

    private String id;
    private PipelineConfiguration pipelineConfiguration;
    private Pipeline pipeline;

    @Override
    public PipelineMetadata.Builder withId(String id) {
      this.id = id;
      return this;
    }

    @Override
    public PipelineMetadata.Builder withPipeline(Pipeline pipeline) {
      this.pipeline = pipeline;
      return this;
    }

    @Override
    public PipelineMetadata build() throws IncompleteException {
      if (id == null) {
        id = UUID.randomUUID().toString();
      }

      if (pipelineConfiguration == null) {
        throw new IncompleteException("Pipeline configuration is not set");
      }


      if (pipeline == null) {
        throw new IncompleteException("Pipeline is not set");
      }

      return new SimplePipelineMetadata(id, pipelineConfiguration, pipeline);
    }


    @Override
    public PipelineMetadata.Builder withConfiguration(PipelineConfiguration pipelineConfiguration) {
      this.pipelineConfiguration = pipelineConfiguration;
      return this;
    }


  }

}
