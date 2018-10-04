/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.feeders;

import io.annot8.core.helpers.WithProcessItem;
import io.annot8.pipelines.listeners.Listenable;
import io.annot8.pipelines.listeners.SourceListener;

public interface ItemFeeder extends Listenable<SourceListener> {

  void feed(WithProcessItem pipeline);

  void close();

}
