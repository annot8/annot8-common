package io.annot8.common.factories;

import io.annot8.core.annotations.Group;
import io.annot8.core.data.Item;

/**
 * Factory to create an group builder.
 *
 * Typically used  in a GroupStore.getBuilder().
 **/
@FunctionalInterface
public interface GroupBuilderFactory {

  /**
   * Create a new builder for the provided item.
   *
   * @return non-null
   */
  Group.Builder create(Item item);

}
