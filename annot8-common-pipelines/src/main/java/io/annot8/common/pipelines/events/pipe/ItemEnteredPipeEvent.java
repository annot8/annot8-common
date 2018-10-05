package io.annot8.common.pipelines.events.pipe;

import io.annot8.core.data.Item;
import io.annot8.common.pipelines.elements.Pipe;

public class ItemEnteredPipeEvent extends AbstractItemPipeEvent {

  public ItemEnteredPipeEvent(Pipe pipe, Item item) {
   super(pipe, item);
  }

}
