package io.annot8.common.implementations.factories;

import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;

public interface ItemCreator {

  default Item create(ItemFactory itemFactory) {
    return create(itemFactory, null);
  }

  Item create(ItemFactory itemFactory, Item parent);
}
