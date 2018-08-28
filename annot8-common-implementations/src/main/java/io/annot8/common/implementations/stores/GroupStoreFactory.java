package io.annot8.common.implementations.stores;

import io.annot8.core.data.Item;
import io.annot8.core.stores.GroupStore;

public interface GroupStoreFactory {

  GroupStore createGroupStore(Item item);

}
