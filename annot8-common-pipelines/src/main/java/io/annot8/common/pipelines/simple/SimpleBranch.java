/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.function.Function;

import io.annot8.common.pipelines.base.AbstractBranch;
import io.annot8.core.data.Item;

public class SimpleBranch extends AbstractBranch {

  private final Function<Item, String> decider;

  public SimpleBranch(Function<Item, String> decider) {
    this.decider = decider;
  }

  @Override
  public boolean forward(Item item) {

    String key = decider.apply(item);

    return sendToBranch(item, key);
  }

  @Override
  public void close() {
    // Do nothing
  }
}
