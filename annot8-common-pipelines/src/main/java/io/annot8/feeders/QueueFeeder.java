/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.feeders;

import io.annot8.core.helpers.WithProcessItem;
import io.annot8.pipelines.events.SourceEvent;
import io.annot8.pipelines.events.source.SourceReadEvent;
import io.annot8.pipelines.listeners.Listeners;
import io.annot8.pipelines.listeners.SourceListener;
import io.annot8.queues.ItemQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.delegates.DelegateContext;
import io.annot8.common.implementations.factories.NotifyingItemFactory;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.Annot8Exception;


public class QueueFeeder implements ItemFeeder {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueueFeeder.class);

  private final ItemQueue queue;

  private final Listeners<SourceListener, SourceEvent> listeners = new Listeners<>(SourceListener::onSourceEvent);

  public QueueFeeder(ItemQueue queue) {
    this.queue = queue;
  }

  public void feed(WithProcessItem pipe) {
    while (queue.hasItems()) {

      // TODO: We don't fire any source events here, but since we don't have a source

      Item item = queue.next();

      try {
        pipe.process(item);
      } catch (Annot8Exception e) {
        LOGGER.error("Failed to process item {} on queue", item.getId(), e);
      }
    }
  }

  @Override
  public void close() {
    // Do nothing
  }

  @Override
  public void register(SourceListener listener) {
    listeners.register(listener);
  }

  @Override
  public void deregister(SourceListener listener) {
    listeners.deregister(listener);
  }

  // TODO: This should be elsewhere
  public Context setupContext(Context context) {
    // Wrap itemFactory with our item factory which will call us back
    final NotifyingItemFactory nif = new NotifyingItemFactory(context.getItemFactory());
    nif.registerListener(queue::add);

    return new DelegateContext(context) {
      @Override
      public ItemFactory getItemFactory() {
        return nif;
      }
    };
  }
}
