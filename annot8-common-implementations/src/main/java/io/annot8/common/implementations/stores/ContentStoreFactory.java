package io.annot8.common.implementations.stores;

import io.annot8.core.data.Item;

public interface ContentStoreFactory {

  ContentStore getContentStore(Item item);

}
