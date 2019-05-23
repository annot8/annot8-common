/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import io.annot8.common.implementations.listeners.Listenable;
import io.annot8.common.pipelines.listeners.SourceListener;
import io.annot8.core.helpers.WithProcessItem;

public interface ItemFeeder extends Listenable<SourceListener> {

  /**
   * Return true if there might be more items in the future (i.e. empty), false if this feeder is
   * now finished (i.e. done).
   */
  boolean feed(WithProcessItem pipeline);

  void close();
}
