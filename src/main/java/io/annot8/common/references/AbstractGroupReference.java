package io.annot8.common.references;

import io.annot8.core.references.GroupReference;
import java.util.Objects;

public abstract class AbstractGroupReference implements GroupReference {
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    if (!GroupReference.class.isAssignableFrom(other.getClass())) {
      return false;
    }

    GroupReference that = (GroupReference) other;
    return Objects.equals(getGroupId(), that.getGroupId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getGroupId());
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [groupId=" + getGroupId() + "]";
  }
}
