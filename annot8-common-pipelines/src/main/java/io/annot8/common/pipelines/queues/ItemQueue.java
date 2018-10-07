/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import java.util.function.Consumer;

import io.annot8.core.data.Item;

public interface ItemQueue extends Consumer<Item> {

  // TODO: Delete this
  void add(Item item);

  @Override
  default void accept(Item item) {
    add(item);
  }

  boolean hasItems();

  Item next();
}
