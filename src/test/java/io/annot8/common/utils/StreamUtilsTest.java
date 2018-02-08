package io.annot8.common.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class StreamUtilsTest {

  @Test
  void castStreamToSubclass() {
    Stream<B> cast = StreamUtils.cast(createMixedStream(), B.class);
    assertEquals(1, cast.count());
  }

  @Test
  void castStreamToParentclass() {
    Stream<A> cast = StreamUtils.cast(createMixedStream(), A.class);
    assertEquals(3, cast.count());
  }

  @Test
  void castStreamToMissingSubclass() {
    Stream<C> cast = StreamUtils.cast(createMixedStream(), C.class);
    assertEquals(0, cast.count());
  }

  private Stream<A> createMixedStream() {
    return Arrays.asList(
        new A(),
        new A(),
        new B()
    ).stream();
  }

  private static class A {

  }

  private static class B extends A {

  }

  private static class C extends A {

  }
}