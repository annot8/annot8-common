package io.annot8.common.implementations.pipelines;

import io.annot8.core.data.Item;

public interface ItemQueue {

  void add(Item item);

  boolean hasItems();

  Item next();

}