package io.annot8.common.references;

import io.annot8.core.annotations.Group;
import io.annot8.core.data.Item;
import io.annot8.core.references.GroupReference;
import java.util.Objects;
import java.util.Optional;

public class LookupGroupReference implements GroupReference {

  private final Item item;

  private final String groupId;

  public LookupGroupReference(Item item, String groupId) {
    this.item = item;
    this.groupId = groupId;
  }

  public static LookupGroupReference to(Item item, Group group) {
    assert group != null;
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
