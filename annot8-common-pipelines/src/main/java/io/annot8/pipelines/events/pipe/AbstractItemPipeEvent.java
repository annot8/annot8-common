package io.annot8.pipelines.events.pipe;

import io.annot8.core.data.Item;
import io.annot8.pipelines.elements.Pipe;
import io.annot8.pipelines.events.PipeEvent;

public abstract class AbstractItemPipeEvent implements PipeEvent {

  private final Pipe pipe;
  private final Item item;

  public AbstractItemPipeEvent(Pipe pipe, Item item) {
    this.pipe = pipe;
    this.item = item;
  }

  public Item getItem() {
    return item;
  }

  public Pipe getPipe() {
    return pipe;
  }
}
