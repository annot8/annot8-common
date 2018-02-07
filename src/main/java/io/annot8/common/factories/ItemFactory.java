package io.annot8.common.factories;

import io.annot8.core.data.Item;

@FunctionalInterface
public interface ItemFactory {

  Item create();

}
