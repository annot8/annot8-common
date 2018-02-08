package io.annot8.common.bounds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.annot8.core.data.Content;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class SpanBoundsTest {

  @Test
  public void testSpanBounds() {
    SpanBounds sb = new SpanBounds(3, 10);

    assertEquals(3, sb.getBegin());
    assertEquals(10, sb.getEnd());
    assertEquals(7, sb.getLength());

    assertNotNull(sb.toString());
  }

  @Test
  public void testNegativeBegin() {
    assertThrows(AssertionError.class, () -> new SpanBounds(-1, 10));
  }

  @Test
  public void testEndLessThanBegin() {
    assertThrows(AssertionError.class, () -> new SpanBounds(10, 5));
  }

  @Test
  public void testIsValidForString() {
    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("1234567890abcde");

    SpanBounds sb = new SpanBounds(5, 10);

    assertTrue(sb.isValid(content));
  }

  @Test
  public void testGetDataForString() {
    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("1234567890abcde");

    SpanBounds sb = new SpanBounds(5, 10);

    Optional<String> data = sb.getData(content, String.class);
    assertEquals("67890", data.get());
  }
}
