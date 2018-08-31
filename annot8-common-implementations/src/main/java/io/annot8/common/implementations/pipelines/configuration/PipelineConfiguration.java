package io.annot8.common.implementations.pipelines.configuration;

import java.util.Collection;
import io.annot8.core.exceptions.IncompleteException;

public interface PipelineConfiguration {

  Collection<PipelineComponentConfiguration> getProcessors();

  Collection<PipelineComponentConfiguration> getSources();

  interface Builder {

    PipelineConfiguration create() throws IncompleteException;

    PipelineConfiguration.Builder withProcessor(PipelineComponentConfiguration processor);

    PipelineConfiguration.Builder withSource(PipelineComponentConfiguration source);

    PipelineConfiguration.Builder withoutProcessor(PipelineComponentConfiguration processor);

    PipelineConfiguration.Builder withoutSource(PipelineComponentConfiguration source);

    default PipelineConfiguration.Builder withProcessors(
        Collection<PipelineComponentConfiguration> processors) {
      processors.forEach(this::withProcessor);
      return this;
    }

    default PipelineConfiguration.Builder withSources(
        Collection<PipelineComponentConfiguration> sources) {
      sources.forEach(this::withSource);
      return this;
    }

    default PipelineConfiguration.Builder withoutProcessors(
        Collection<PipelineComponentConfiguration> processors) {
      processors.forEach(this::withoutProcessor);
      return this;
    }

    default PipelineConfiguration.Builder withoutSources(
        Collection<PipelineComponentConfiguration> sources) {
      sources.forEach(this::withoutSource);
      return this;
    }

  }

}
