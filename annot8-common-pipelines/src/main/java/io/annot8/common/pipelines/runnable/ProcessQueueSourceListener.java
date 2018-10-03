/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.runnable;

import io.annot8.common.pipelines.common.ProcessingPipe;
import io.annot8.common.pipelines.common.SourceListener;
import io.annot8.common.pipelines.queues.QueueProcessor;
import io.annot8.core.components.Source;

public class ProcessQueueSourceListener implements SourceListener {

  private final QueueProcessor queueProcessor;
  private final ProcessingPipe pipe;

  public ProcessQueueSourceListener(QueueProcessor queueProcessor, ProcessingPipe pipe) {
    this.queueProcessor = queueProcessor;
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
    if (queueProcessor != null && pipe != null) {
      queueProcessor.process(pipe);
    }
  }
}
