package io.annot8.common.implementations.references;

import io.annot8.core.references.AnnotationReference;
import java.util.Objects;

/**
 * Abstract implementation of AnnotationReference, providing correct implementations of equals,
 * hashCode and toString.
 *
 * Two annotation references are taken to be equal if the following properties are all equal. The
 * actual implementation of the annotation reference is seen to be irrelevant and not checked.
 * <ul>
 * <li>annotationId</li>
 * <li>contentName</li>
 * </ul>
 */
public abstract class AbstractAnnotationReference implements AnnotationReference {

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
    return this.getClass().getName() + " [annotationId=" + getAnnotationId() + ", contentName="
        + getContentName() + "]";
  }
}
