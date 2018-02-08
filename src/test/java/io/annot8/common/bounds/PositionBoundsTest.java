package io.annot8.common.bounds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.annot8.core.data.Content;
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
    assertThrows(AssertionError.class, () -> new PositionBounds(-1));
  }

  @Test
  void zeroIsValid() {
    PositionBounds bounds = new PositionBounds(0);
    assertEquals(0, bounds.getPosition());
  }

  @Test
  void testGetData() {
    PositionBounds bounds = new PositionBounds(0);
    assertFalse(bounds.getData(null, null).isPresent());
  }

  @Test
  void testIsValidForString() {
    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("test");

    PositionBounds bounds = new PositionBounds(0);

    assertTrue(bounds.isValid(content));

  }

}