package io.annot8.common.bounds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.annot8.common.bounds.SpansBounds.Spans;
import io.annot8.core.data.Content;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class SpansBoundsTest {


  @Test
  void newNull() {
    assertThrows(AssertionError.class, () -> new SpansBounds((Collection<SpanBounds>) null));
  }

  @Test
  void newEmpty() {
    SpansBounds b = new SpansBounds(Collections.emptyList());

    assertEquals(0, b.getSize());
    assertNotNull(b.toString());
  }

  @Test
  void newSpans() {
    SpanBounds s1 = new SpanBounds(0, 3);
    SpanBounds s2 = new SpanBounds(5, 8);

    SpansBounds b = new SpansBounds(s1, s2);

    assertEquals(2, b.getSize());
    List<SpanBounds> spanBounds = b.getSpans().collect(Collectors.toList());

    assertEquals(2, spanBounds.size());
    assertEquals(s1, spanBounds.get(0));
    assertEquals(s2, spanBounds.get(1));

    Content<String> content = mock(Content.class);
    when(content.getData()).thenReturn("1234567890");

    assertTrue(b.isValid(content));

    Spans data = b.getData(content, Spans.class).get();
    assertEquals(2, data.getSize());
    assertEquals("123", data.getSpans().findFirst().get());
    assertEquals("678", data.getSpans().skip(1).findFirst().get());

  }


}