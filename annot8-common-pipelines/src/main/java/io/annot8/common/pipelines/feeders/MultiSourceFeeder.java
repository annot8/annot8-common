/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import io.annot8.core.helpers.WithProcessItem;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.annot8.common.pipelines.common.SourceListener;
import io.annot8.core.components.Source;
import io.annot8.core.data.ItemFactory;

public class MultiSourceFeeder implements SourceFeeder {

  private final Collection<Source> sources;

  private final Set<SourceListener> listeners = new CopyOnWriteArraySet<>();

  public MultiSourceFeeder(Source... sources) {
    this(Arrays.asList(sources));
  }

  public MultiSourceFeeder(Collection<Source> sources) {
    this.sources = sources;
  }

  @Override
  public void feed(ItemFactory itemFactory, WithProcessItem processor) {
    for (Source source : sources) {
      SingleSourceFeeder feeder = new SingleSourceFeeder(source);
      listeners.forEach(feeder::register);

      feeder.feed(itemFactory, processor);
    }
  }

  @Override
  public void close() {
    sources.forEach(Source::close);
  }

  // TODO: these wont act on the current feeder, I think feed is wrong (shouldn't take itemfactory)

  @Override
  public void register(SourceListener listener) {
    listeners.add(listener);
  }

  @Override
  public void unregister(SourceListener listener) {
    listeners.remove(listener);
  }
}