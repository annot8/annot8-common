package io.annot8.common.references;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.annot8.core.annotations.Group;
import org.junit.jupiter.api.Test;

class WrappedGroupReferenceTest {

  @Test
  void newReference() {
    Group group = mock(Group.class);
    when(group.getId()).thenReturn("2");

    WrappedGroupReference reference = new WrappedGroupReference(group);

    assertEquals("2", reference.getGroupId());
    verify(group, only()).getId();

    assertSame(group, reference.toGroup().get());
  }


}