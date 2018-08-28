package io.annot8.common.implementations.stores;

import io.annot8.core.data.Item;

@FunctionalInterface
public interface ContentStoreFactory {

  ContentStore create(Item item);

}
