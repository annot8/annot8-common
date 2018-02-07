package io.annot8.common.factories;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.data.Item;
import io.annot8.core.stores.AnnotationStore;

/**
 * Factory to create an annotation builder.
 *
 * Typically used  in an AnnotationStore.getBuilder().
 **/
@FunctionalInterface
public interface AnnotationBuilderFactory {

  /**
   * Create a new builder for the provided parameters.
   *
   * Most implementation will simply need the store parameter to allow save on save.
   *
   * @return non-null
   */
  Annotation.Builder create(Item item, String content, AnnotationStore store);
}
