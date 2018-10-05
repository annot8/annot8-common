/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.pipelines.pipeline;

import io.annot8.feeders.QueueFeeder;
import io.annot8.pipelines.elements.Pipe;
import io.annot8.pipelines.events.SourceEvent;
import io.annot8.pipelines.listeners.SourceListener;

public class ProcessQueueSourceListener implements SourceListener {

  private final QueueFeeder queueFeeder;
  private final Pipe pipe;

  public ProcessQueueSourceListener(QueueFeeder queuePusher, Pipe pipe) {
    this.queueFeeder = queuePusher;
    this.pipe = pipe;
  }

  @Override
  public void onSourceEvent(SourceEvent event) {
    processQueue();
  }

  protected void processQueue() {
    if (queueFeeder != null && pipe != null) {
      queueFeeder.feed(pipe);
    }
  }
}
