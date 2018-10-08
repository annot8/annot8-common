/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import io.annot8.common.implementations.data.WrappingBaseItemToItem;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.BaseItemFactory;
import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;

public class SimpleItemFactory implements ItemFactory {

  private final BaseItemFactory baseItemFactory;
  private final WrappingBaseItemToItem converter;

  public SimpleItemFactory(BaseItemFactory baseItemFactory, WrappingBaseItemToItem converter) {
    this.baseItemFactory = baseItemFactory;
    this.converter = converter;
  }

  @Override
  public Item create() {
    final BaseItem createdItem = baseItemFactory.create();
    return converter.convert(createdItem);
  }
}
