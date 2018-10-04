/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.runnable;

import io.annot8.pipelines.simple.SimplePipe;
import io.annot8.feeders.QueuePusher;
import io.annot8.core.components.Source;

public class ProcessQueueSourceListener implements SourceListener {

  private final QueuePusher queuePusher;
  private final SimplePipe pipe;

  public ProcessQueueSourceListener(QueuePusher queuePusher, SimplePipe pipe) {
    this.queuePusher = queuePusher;
    this.pipe = pipe;
  }

  @Override
  public void onSourceDone(Source source) {
    processQueue();
  }

  @Override
  public void onSourceEmpty(Source source) {
    processQueue();
  }

  @Override
  public void onSourceError(Source source) {
    processQueue();
  }

  @Override
  public void onSourceRead(Source source) {
    processQueue();
  }

  protected void processQueue() {
    if (queuePusher != null && pipe != null) {
      queuePusher.process(pipe);
    }
  }
}
