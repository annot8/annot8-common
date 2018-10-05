package io.annot8.common.pipelines.events.pipe;

import io.annot8.core.data.Item;
import io.annot8.common.pipelines.elements.Pipe;

public class ItemExitedPipeEvent extends AbstractItemPipeEvent {

  public ItemExitedPipeEvent(Pipe pipe, Item item) {
   super(pipe, item);
  }

}
