package io.annot8.common.factories;

import io.annot8.common.stores.SaveFromBuilder;
import io.annot8.core.annotations.Annotation;
import io.annot8.core.stores.AnnotationStore;

/**
 * Factory to create an annotation builder.
 *
 * Typically used  in an AnnotationStore.getBuilder().
 **/
@FunctionalInterface
public interface AnnotationBuilderFactory<T> {

  /**
   * Create a new builder for the provided parameters.
   *
   * Most implementation will simply need the store parameter to allow save on save.
   *
   * @return non-null
   */
  Annotation.Builder create(String content, AnnotationStore store,
      SaveFromBuilder<T, Annotation> saver);
}
