package io.annot8.common.references.impl;

import io.annot8.common.references.AnnotationReference;
import io.annot8.core.annotations.Annotation;
import java.util.Objects;
import java.util.Optional;

/**
 * TODO: ...Before you use this you need to understand that annoations change, so this is not going
 * to provide you with the most upto date annoation. Nor will it work if your annotation is deleted
 * from the store.
 */
public class WrappedAnnotationReference implements AnnotationReference {

  private final Annotation annotation;

  public WrappedAnnotationReference(Annotation annotation) {
    assert annotation != null;
    this.annotation = annotation;
  }

  @Override
  public Optional<Annotation> toAnnotation() {
    return Optional.of(annotation);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WrappedAnnotationReference that = (WrappedAnnotationReference) o;

    return Objects.equals(annotation.getContentName(), that.annotation.getContentName()) &&
        Objects.equals(annotation.getId(), that.annotation.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotation.getContentName(), annotation.getId());
  }
}
