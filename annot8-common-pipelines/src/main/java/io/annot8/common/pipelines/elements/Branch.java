package io.annot8.common.pipelines.elements;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.data.Item;
import java.util.function.Consumer;

public interface Branch extends AutoCloseable {

  boolean forward(Item item);

  void addBranch(String key, Consumer<Item> queue);

  void removeBranch(String key, Consumer<Item> queue);

}
