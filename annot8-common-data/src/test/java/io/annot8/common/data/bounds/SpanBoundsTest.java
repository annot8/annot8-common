package io.annot8.common.data.bounds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.annot8.core.data.Content;
import io.annot8.core.exceptions.Annot8RuntimeException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class SpanBoundsTest {

  @Test
  void testSpanBounds() {
    SpanBounds sb = new SpanBounds(3, 10);

    assertEquals(3, sb.getBegin());
    assertEquals(10, sb.getEnd());
    assertEquals(7, sb.getLength());

    assertNotNull(sb.toString());
  }

  @Test
  void testNegativeBegin() {
    assertThrows(Annot8RuntimeException.class, () -> new SpanBounds(-1, 10));
  }

  @Test
  void testEndLessThanBegin() {
    assertThrows(Annot8RuntimeException.class, () -> new SpanBounds(10, 5));
  }

  @Test
  void testIsValidForString() {
    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("1234567890abcde");

    SpanBounds sb = new SpanBounds(5, 10);

    assertTrue(sb.isValid(content));
  }

  @Test
  void testGetDataForString() {
    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("1234567890abcde");

    SpanBounds sb = new SpanBounds(5, 10);

    Optional<String> data = sb.getData(content, String.class);
    assertEquals("67890", data.get());
  }
}
