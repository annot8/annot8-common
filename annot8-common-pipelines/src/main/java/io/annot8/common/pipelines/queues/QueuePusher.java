/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import io.annot8.core.helpers.WithProcessItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.delegates.DelegateContext;
import io.annot8.common.implementations.factories.NotifyingItemFactory;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.Annot8Exception;


public class QueuePusher {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueuePusher.class);

  private final ItemQueue queue;

  public QueuePusher(ItemQueue queue) {
    this.queue = queue;
  }

  public void process(WithProcessItem pipe) {
    while (queue.hasItems()) {
      Item item = queue.next();
      try {
        pipe.process(item);
      } catch (Annot8Exception e) {
        LOGGER.error("Failed to process item {} on queue", item.getId(), e);
      }
    }
  }

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
