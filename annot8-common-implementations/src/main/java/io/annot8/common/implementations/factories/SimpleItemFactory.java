/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.factories;

import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;

public class SimpleItemFactory implements ItemFactory {

  private final ItemCreator creator;

  public SimpleItemFactory(ItemCreator creator) {
    this.creator = creator;
  }

  @Override
  public Item create() {
    return creator.create(this);
  }

  @Override
  public Item create(Item parent) {
    return creator.create(this, parent);
  }
}
