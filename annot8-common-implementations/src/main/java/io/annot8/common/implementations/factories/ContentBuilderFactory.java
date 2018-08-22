package io.annot8.common.implementations.factories;

import io.annot8.common.implementations.stores.SaveCallback;
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
   * @param item the item owning this content
   * @param saver the save callback used by builder
   * @return non-null builder
   */
  Content.Builder<C, D> create(Item item, SaveCallback<C, C> saver);

  /**
   * Get the class of the data this content holds
   *
   * @return the data class
   */
  Class<D> getDataClass();

  /**
   * Get the content class created.
   *
   * @return content class (being implemented)
   */
  Class<C> getContentClass();

}