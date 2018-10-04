package io.annot8.pipelines.simple;

import io.annot8.core.data.Item;
import io.annot8.pipelines.elements.Merge;
import io.annot8.queues.ItemQueue;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleMerge implements Merge {

  private final Consumer<Item> consumer;
  private final Predicate<Item> predicate;

  public SimpleMerge(Consumer<Item> consumer) {
    this(consumer, null);
  }

  public SimpleMerge(Consumer<Item> consumer, Predicate<Item> predicate) {
    this.predicate = predicate;
    Objects.requireNonNull(consumer);
    this.consumer = consumer;
  }

  @Override
  public boolean receive(Item item) {
    if(predicate != null && !predicate.test(item)) {
      return false;
    }

    consumer.accept(item);

    return true;
  }

  @Override
  public void close() {
    // Do nothing
  }
}
