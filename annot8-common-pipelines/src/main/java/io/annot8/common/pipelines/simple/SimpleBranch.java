package io.annot8.common.pipelines.simple;

import io.annot8.common.pipelines.base.AbstractBranch;
import io.annot8.core.data.Item;
import java.util.function.Function;

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
