package io.annot8.common.utils;

import java.util.stream.Stream;

/**
 * Utilities for working with streams
 */
public final class StreamUtils {

  private StreamUtils() {
    // Singleton
  }

  /**
   * Filter out items in the stream that aren't a subclass of clazz
   */
  public static <T, S extends T> Stream<S> cast(Stream<T> stream, Class<S> clazz) {
    return stream.filter(clazz::isInstance)
        .map(clazz::cast);
  }
}
