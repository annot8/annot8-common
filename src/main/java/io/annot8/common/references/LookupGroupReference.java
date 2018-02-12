package io.annot8.common.references;

import java.util.Objects;
import java.util.Optional;
import io.annot8.core.annotations.Group;
import io.annot8.core.data.Item;
import io.annot8.core.references.GroupReference;

/**
 * A reference which will always retrieve the latest group from the appropriate group store.
 *
 * Does not hold a reference to the group.
 */
public class LookupGroupReference implements GroupReference {

  private final Item item;

  private final String groupId;

  /**
   * New reference either from another reference or manually created.
   */
  public LookupGroupReference(Item item, String groupId) {
    this.item = item;
    this.groupId = groupId;
  }

  /**
   * Create a reference from a group instance.
   */
  public static LookupGroupReference to(Item item, Group group) {
    return new LookupGroupReference(item, group.getId());
  }

  @Override
  public String getGroupId() {
    return groupId;
  }

  @Override
  public Optional<Group> toGroup() {
    return item.getGroups()
        .getById(groupId);
  }

  @Override
  public boolean equals(Object o) {
    return sameReference(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId);
  }
}
