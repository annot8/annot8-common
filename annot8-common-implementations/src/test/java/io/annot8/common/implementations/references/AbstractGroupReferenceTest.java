package io.annot8.common.implementations.references;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import io.annot8.core.annotations.Group;
import io.annot8.core.references.GroupReference;

public class AbstractGroupReferenceTest {

  @Test
  public void testEquals() {
    GroupReference ref = new TestGroupReference("groupId");
    GroupReference same = new TestGroupReference("groupId");
    GroupReference diff = new TestGroupReference("diff");

    assertTrue(ref.equals(ref));
    assertTrue(ref.equals(same));
    assertEquals(ref.hashCode(), same.hashCode());
    assertFalse(ref.equals(diff));
    assertFalse(ref.equals(new Object()));
    assertFalse(ref.equals(null));
  }

  private class TestGroupReference extends AbstractGroupReference {

    private String groupId;

    public TestGroupReference(String groupId) {
      this.groupId = groupId;
    }

    @Override
    public String getGroupId() {
      return groupId;
    }

    @Override
    public Optional<Group> toGroup() {
      throw new UnsupportedOperationException("Unsupported in this test implementation");
    }

  }

}
