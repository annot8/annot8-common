/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.Objects;
import java.util.function.Predicate;

import io.annot8.common.pipelines.elements.Merge;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.ProcessorResponse.Status;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.helpers.WithProcessItem;

public class SimpleMerge implements Merge {

  private final WithProcessItem consumer;
  private final Predicate<Item> predicate;

  public SimpleMerge(WithProcessItem consumer) {
    this(consumer, null);
  }

  public SimpleMerge(WithProcessItem consumer, Predicate<Item> predicate) {
    this.predicate = predicate;
    Objects.requireNonNull(consumer);
    this.consumer = consumer;
  }

  @Override
  public boolean receive(Item item) throws Annot8Exception {
    if (predicate != null && !predicate.test(item)) {
      return false;
    }

    final ProcessorResponse response = consumer.process(item);

    return response.getStatus() == Status.OK;
  }

  @Override
  public void close() {
    // Do nothing
  }
}
