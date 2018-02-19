package io.annot8.common.annotations;

import io.annot8.core.annotations.Group;
import io.annot8.core.references.AnnotationReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractGroup implements Group{

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null) {
      return false;
    }

    if (!(other instanceof Group)) {
      return false;
    }

    Group g = (Group) other;

    // TODO: This is a lot of work... we can't compare the streams directly
    Map<String, Set<AnnotationReference>> ourReferences = getReferences().entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().collect(Collectors.toSet())));
    Map<String, Set<AnnotationReference>> otherReferences = getReferences().entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().collect(Collectors.toSet())));

    return Objects.equals(getId(), g.getId())
        && Objects.equals(getType(), g.getType())
        && Objects.equals(getProperties(), g.getProperties())
        && Objects.equals(ourReferences, otherReferences);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getType(), getProperties(), getReferences());
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [id=" + getId() + ", type=" + getType() + ", properties="
        + getProperties() + ", annotations=" + getAnnotations() + "]";
  }
}
