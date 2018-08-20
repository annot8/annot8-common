package io.annot8.common.implementations.factories;

import io.annot8.common.implementations.stores.SaveCallback;
import io.annot8.core.annotations.Group;
import io.annot8.core.data.Item;
import io.annot8.core.stores.GroupStore;

/**
 * Factory to create an group builder.
 *
 * Typically used in a GroupStore.getBuilder().
 **/
@FunctionalInterface
public interface GroupBuilderFactory<T> {

  /**
   * Create a new builder for the provided item.
   *
   * @return non-null
   */
  Group.Builder create(Item item, GroupStore groupStore, SaveCallback<T, Group> saver);

}
