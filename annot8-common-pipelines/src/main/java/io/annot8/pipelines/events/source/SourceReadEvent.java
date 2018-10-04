package io.annot8.pipelines.events.source;

import io.annot8.core.components.Source;
import io.annot8.core.data.Item;

public class SourceReadEvent extends AbstractSourceEvent{

  public SourceReadEvent(Source source) {
    super(source);
  }
}
