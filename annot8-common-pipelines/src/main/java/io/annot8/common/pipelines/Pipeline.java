/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines;

import io.annot8.core.context.Context;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;
import io.annot8.core.helpers.WithId;

public interface Pipeline extends WithId, AutoCloseable {

  default void configure(final Context context)
      throws BadConfigurationException, MissingResourceException {
    // Do nothing
  }

  void register(ProcessingListener listener);

  void unregister(ProcessingListener listener);
}
