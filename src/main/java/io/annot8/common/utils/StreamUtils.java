package io.annot8.common.utils;

import java.util.stream.Stream;

public final class StreamUtils {

  private StreamUtils() {
    // Singleton
  }

  public static <T, S extends T> Stream<S> cast(Stream<T> stream, Class<S> clazz) {
    return stream.filter(clazz::isInstance)
        .map(clazz::cast);
  }
}
