package io.annot8.common.implementations.pipelines.management;

import java.util.Optional;

public interface PipelineMetadataStore {

  /**
   * 
   * @param id - ID of the PipelineMetadata to retrieve
   * @return PipelineMetadata for the given identifier
   */
  Optional<PipelineMetadata> getMetadata(String id);

  /**
   *
   * Saves or updates the provided pipeline metadata
   *
   * @param pipelineMetadata
   * @return The stored PipelineMetadata
   */
  PipelineMetadata saveMetadata(PipelineMetadata pipelineMetadata);

}
