package io.annot8.pipelines.simple;

import io.annot8.core.data.Item;
import io.annot8.pipelines.elements.Branch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
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
