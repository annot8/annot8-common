package io.annot8.common.registries;

import io.annot8.common.factories.ContentBuilderFactory;
import io.annot8.core.data.Content;
import java.util.Optional;

/**
 * A registry of content builder factories.
 */
public interface ContentBuilderFactoryRegistry {

  /**
   * Get the (best) content builder factory for the content class requested, if available.
   */
  <D, C extends Content<D>> Optional<ContentBuilderFactory<D, C>> get(Class<C> contentClass);
}
