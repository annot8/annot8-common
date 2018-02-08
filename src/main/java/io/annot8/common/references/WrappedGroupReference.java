package io.annot8.common.references;

import io.annot8.core.annotations.Group;
import io.annot8.core.references.GroupReference;
import java.util.Objects;
import java.util.Optional;

/**
 * Wrap an annotation into a annotation reference, holding it in memory.
 *
 * Before you use this you need to understand that group change, so this is not going to
 * provide you with the most up to date group. Nor will it register if your group is deleted from the
 * store.
 *
 * This should be used in only special cases - instead prefer {@link LookupGroupReference}.
 * A special case might be a for loop where you know nothing will get deleted or changed, but then
 * why not use the Group itself?
 */
public class WrappedGroupReference implements GroupReference {


  private final Group group;

  /**
   * Create a reference holding this group
   */
  public WrappedGroupReference(Group group) {
    this.group = group;
  }

  @Override
  public String getGroupId() {
    return group.getId();
  }

  @Override
  public Optional<Group> toGroup() {
    return Optional.ofNullable(group);
  }


  @Override
  public boolean equals(Object o) {
    return sameReference(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(group.getId());
  }
}
