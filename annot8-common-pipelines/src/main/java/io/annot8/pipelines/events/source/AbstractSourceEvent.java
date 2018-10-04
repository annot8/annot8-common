package io.annot8.pipelines.events.source;

import io.annot8.core.components.Source;
import io.annot8.pipelines.events.SourceEvent;

public abstract class AbstractSourceEvent implements SourceEvent {

  private final Source source;

  protected AbstractSourceEvent(Source source) {
    this.source = source;
  }

  public Source getSource() {
    return source;
  }
}
