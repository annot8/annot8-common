package io.annot8.common.references;

import io.annot8.core.references.AnnotationReference;
import java.util.Objects;

public abstract class AbstractAnnotationReference implements AnnotationReference{
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    if (!AnnotationReference.class.isAssignableFrom(other.getClass())) {
      return false;
    }

    AnnotationReference that = (AnnotationReference) other;
    return Objects.equals(getAnnotationId(), that.getAnnotationId()) && Objects
        .equals(getContentName(), that.getContentName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAnnotationId(), getContentName());
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [annotationId=" + getAnnotationId() + ", contentName=" + getContentName() + "]";
  }
}
