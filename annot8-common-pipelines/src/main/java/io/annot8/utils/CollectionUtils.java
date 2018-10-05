/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.utils;

import java.util.Collection;
import java.util.Collections;

public class CollectionUtils {

  private CollectionUtils() {
    // Singleton
  }

  public static <T> Collection<T> nonNullCollection(Collection<T> configuration) {
    return configuration == null ? Collections.emptySet() : configuration;
  }
}
