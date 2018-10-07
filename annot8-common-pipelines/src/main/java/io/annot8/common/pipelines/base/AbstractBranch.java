/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import io.annot8.common.pipelines.elements.Branch;
import io.annot8.core.data.Item;

public abstract class AbstractBranch implements Branch {

  private final Map<String, Consumer<Item>> branches = new HashMap<>();

  @Override
  public void addBranch(String key, Consumer<Item> queue) {
    branches.put(key, queue);
  }

  @Override
  public void removeBranch(String key, Consumer<Item> queue) {
    branches.remove(key);
  }

  protected boolean sendToBranch(Item item, String key) {
    final Consumer<Item> consumer = branches.get(key);
    if (consumer == null) {
      return false;
    } else {
      consumer.accept(item);
      return true;
    }
  }

  protected boolean sendToBranches(Item item, String... keys) {
    if (keys.length == 0) {
      return true;
    }

    long found = Arrays.stream(keys).map(k -> sendToBranch(item, k)).filter(b -> b).count();

    // As long as we've sent it somewhere..
    return found > 0;
  }
}
