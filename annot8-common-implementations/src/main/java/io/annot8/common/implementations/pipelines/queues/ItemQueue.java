/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.queues;

import io.annot8.core.data.Item;

public interface ItemQueue {

  void add(Item item);

  boolean hasItems();

  Item next();
}
