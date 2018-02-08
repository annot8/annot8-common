package io.annot8.common.factories;

import io.annot8.common.stores.SaveFromBuilder;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;

/**
 * Factory to create an content builder.
 *
 * Typically used  in a Item.createContent().
 **/
public interface ContentBuilderFactory<D, C extends Content<D>> {

  /**
   * Create a new builder for the provided item.
   *
   * @return non-null
   */
  Content.Builder<C, D> create(Item item, SaveFromBuilder<C, C> saver);

  /**
   * Get the class of the data this content holds
   */
  Class<D> getDataClass();

  /**
   * Get the content class created.
   */
  Class<C> getContentClass();

}
