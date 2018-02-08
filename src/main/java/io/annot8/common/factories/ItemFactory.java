package io.annot8.common.factories;

import io.annot8.core.data.Item;

/**
 * Factory to create new items
 */
@FunctionalInterface
public interface ItemFactory {

  /**
   * Create a new item
   *
   * @return non-null
   */
  Item create();

}
