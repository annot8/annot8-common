package io.annot8.pipelines.events.pipe;

import io.annot8.core.data.Item;
import io.annot8.pipelines.elements.Pipe;

public class ItemErrorPipeEvent extends AbstractItemPipeEvent {

  public ItemErrorPipeEvent(Pipe pipe, Item item) {
   super(pipe, item);
  }

}
