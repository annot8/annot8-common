/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import io.annot8.common.pipelines.common.SourceListener;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.helpers.WithProcessItem;

public interface SourceFeeder {

  void feed(ItemFactory itemFactory, WithProcessItem pipeline);

  void close();

  void register(SourceListener listener);

  void unregister(SourceListener listener);
}
