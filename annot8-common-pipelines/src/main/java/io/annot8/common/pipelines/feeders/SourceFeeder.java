/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import io.annot8.common.pipelines.common.ItemProcessor;
import io.annot8.common.pipelines.common.SourceListener;
import io.annot8.core.data.ItemFactory;

public interface SourceFeeder {

  void feed(ItemFactory itemFactory, ItemProcessor pipeline);

  void close();

  void register(SourceListener listener);

  void unregister(SourceListener listener);
}
