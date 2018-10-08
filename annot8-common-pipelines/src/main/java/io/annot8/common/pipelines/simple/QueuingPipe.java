/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import io.annot8.common.implementations.listeners.Deregister;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.common.pipelines.listeners.PipeListener;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

public class QueuingPipe implements Pipe {

  private final Pipe delegate;
  private final QueueFeeder queueFeeder;

  public QueuingPipe(QueueFeeder queueFeeder, Pipe delegate) {
    this.delegate = delegate;
    this.queueFeeder = queueFeeder;
  }

  @Override
  public String getName() {
    return delegate.getName();
  }

  @Override
  public ProcessorResponse process(Item item) throws Annot8Exception {
    ProcessorResponse response = delegate.process(item);

    queueFeeder.feed(delegate);

    return response;
  }

  @Override
  public Deregister register(PipeListener listener) {
    return delegate.register(listener);
  }

  @Override
  public void deregister(PipeListener listener) {
    delegate.deregister(listener);
  }

  @Override
  public void close() {
    delegate.close();
  }
}
