package io.annot8.common.bounds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.annot8.core.data.Content;
import io.annot8.core.exceptions.Annot8RuntimeException;
import org.junit.jupiter.api.Test;

class PositionBoundsTest {

  @Test
  void testNewPosition() {

    PositionBounds bounds = new PositionBounds(34);

    assertEquals(34, bounds.getPosition());

    assertNotNull(bounds.toString());
  }

  @Test
  void negativeIsInvalid() {
    assertThrows(Annot8RuntimeException.class, () -> new PositionBounds(-1));
  }

  @Test
  void zeroIsValid() {
    PositionBounds bounds = new PositionBounds(0);
    assertEquals(0, bounds.getPosition());
  }

  @Test
  void testGetDataForString() {
    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("test");

    PositionBounds bounds = new PositionBounds(0);
    assertTrue(bounds.getData(content, String.class).isPresent());
  }

  @Test
  void testIsValidForString() {
    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("test");

    PositionBounds bounds = new PositionBounds(0);

    assertTrue(bounds.isValid(content));

  }

}