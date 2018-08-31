package io.annot8.common.implementations.pipelines.management;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePipelineMetadataStore implements PipelineMetadataStore {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SimplePipelineMetadataStore.class);

  private Map<String, PipelineMetadata> store;

  public SimplePipelineMetadataStore() {
    this.store = new HashMap<>();
  }

  @Override
  public Optional<PipelineMetadata> getMetadata(String id) {
    if (!store.containsKey(id)) {
      return Optional.empty();
    }
    return Optional.of(store.get(id));
  }

  @Override
  public PipelineMetadata saveMetadata(PipelineMetadata pipelineMetadata) {
    store.put(pipelineMetadata.getId(), pipelineMetadata);
    return pipelineMetadata;
  }

}
