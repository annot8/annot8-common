package io.annot8.common.implementations.factories;

import io.annot8.common.implementations.stores.SaveCallback;
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
   * @param content the content name
   * @param store the annotation store to use
   * @param saver save callback (used by the builder)
   * @return non-null builder
   */
  Annotation.Builder create(String content, AnnotationStore store,
      SaveCallback<T, Annotation> saver);
}
