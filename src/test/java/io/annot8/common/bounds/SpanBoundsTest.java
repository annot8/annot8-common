package io.annot8.common.bounds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpanBoundsTest {

  @Test
  public void testSpanBounds() {
    SpanBounds sb = new SpanBounds(5, 10);

    Assertions.assertEquals(5, sb.getBegin());
    Assertions.assertEquals(10, sb.getEnd());
  }
}
