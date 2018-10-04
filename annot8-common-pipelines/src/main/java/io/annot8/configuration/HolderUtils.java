/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.configuration;

import java.util.Collection;
import java.util.Collections;

public class HolderUtils {

  private HolderUtils() {
    // Singleton
  }

  public static <T> Collection<T> nonNullCollection(Collection<T> configuration) {
    return configuration == null ? Collections.emptySet() : configuration;
  }
}
