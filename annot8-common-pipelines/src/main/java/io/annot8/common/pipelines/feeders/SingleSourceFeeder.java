/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import io.annot8.core.helpers.WithProcessItem;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

import io.annot8.common.pipelines.common.SourceListener;
import io.annot8.core.components.Source;
import io.annot8.core.components.responses.SourceResponse;
import io.annot8.core.data.ItemFactory;

public class SingleSourceFeeder implements SourceFeeder {

  private final Source source;

  private final Set<SourceListener> listeners = new CopyOnWriteArraySet<>();

  public SingleSourceFeeder(Source source) {
    this.source = source;
  }

  public void feed(ItemFactory itemFactory, WithProcessItem pipeline) {
    SourceResponse.Status status;
    do {
      final SourceResponse response = source.read(itemFactory);
      status = response.getStatus();

      switch (status) {
        case OK:
          fireListeners(l -> l.onSourceRead(source));
          break;
        case EMPTY:
          fireListeners(l -> l.onSourceEmpty(source));
          break;
        case DONE:
          fireListeners(l -> l.onSourceDone(source));
          break;
        case SOURCE_ERROR:
          fireListeners(l -> l.onSourceError(source));
          break;
      }

    } while (status == SourceResponse.Status.OK || status == SourceResponse.Status.EMPTY);

    close();
  }

  public void close() {
    source.close();
  }

  @Override
  public void register(SourceListener listener) {
    listeners.add(listener);
  }

  @Override
  public void unregister(SourceListener listener) {
    listeners.remove(listener);
  }

  protected void fireListeners(Consumer<SourceListener> consumer) {
    this.listeners.forEach(consumer);
  }
}
