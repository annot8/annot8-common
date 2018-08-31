package io.annot8.common.implementations.pipelines.configuration;

import java.util.Collection;

public interface PipelineConfiguration {

  Collection<ComponentConfiguration> getProcessors();

  Collection<ComponentConfiguration> getSources();

  Collection<ComponentConfiguration> getResources();

}
