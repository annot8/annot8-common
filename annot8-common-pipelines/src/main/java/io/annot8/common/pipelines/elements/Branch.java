/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import java.util.function.Consumer;

import io.annot8.core.data.Item;

public interface Branch extends AutoCloseable {

  boolean forward(Item item);

  void addBranch(String key, Consumer<Item> queue);

  void removeBranch(String key, Consumer<Item> queue);
}
