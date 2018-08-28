package io.annot8.common.implementations.stores;

import io.annot8.core.data.Item;
import io.annot8.core.stores.GroupStore;

@FunctionalInterface
public interface GroupStoreFactory {

  GroupStore create(Item item);

}
