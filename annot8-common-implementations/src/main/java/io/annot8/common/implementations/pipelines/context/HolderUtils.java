/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.context;

import java.util.Collection;
import java.util.Collections;

import io.annot8.core.settings.Settings;

public class HolderUtils {

  private HolderUtils() {
    // Singleton
  }

  public static Collection<Settings> nonNullCollection(Collection<Settings> configuration) {
    return configuration == null ? Collections.emptySet() : configuration;
  }
}
