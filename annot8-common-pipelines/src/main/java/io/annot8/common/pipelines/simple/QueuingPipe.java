package io.annot8.common.pipelines.simple;

import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.listeners.PipeListener;
import io.annot8.common.pipelines.queues.ItemQueue;

public class QueuingPipe implements Pipe {

  private final Pipe delegate;
  private final QueueFeeder queueFeeder;

  public QueuingPipe(ItemQueue queue, Pipe delegate) {
    this.delegate  = delegate;
    this.queueFeeder = new QueueFeeder(queue);
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
  public void register(PipeListener listener) {
    delegate.register(listener);
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
