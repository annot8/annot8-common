package io.annot8.pipelines.events.source;

import io.annot8.core.components.Source;

public class SourceDoneEvent extends AbstractSourceEvent{

  public SourceDoneEvent(Source source) {
    super(source);
  }

}
