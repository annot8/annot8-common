package io.annot8.common.annotations;

import io.annot8.core.annotations.Annotation;
import java.util.Objects;

public abstract class AbstractAnnotation implements Annotation {

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    if (!(other instanceof Annotation)) {
      return false;
    }

    Annotation a = (Annotation) other;

    return Objects.equals(getId(), a.getId())
        && Objects.equals(getType(), a.getType())
        && Objects.equals(getProperties(), a.getProperties())
        && Objects.equals(getBounds(), a.getBounds())
        && Objects.equals(getContentName(), a.getContentName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getType(), getProperties(), getBounds(), getContentName());
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [id=" + getId() + ", type=" + getType() + ", contentName=" + getContentName()
        + ", bounds=" + getBounds() + ", properties=" + getProperties() + "]";
  }
}