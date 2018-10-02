/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.feeders;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import io.annot8.common.implementations.pipelines.common.ItemProcessor;
import io.annot8.common.implementations.pipelines.common.SourceListener;
import io.annot8.core.components.Source;
import io.annot8.core.data.ItemFactory;

public class MultiSourceFeeder implements SourceFeeder {

  private final Collection<Source> sources;

  private final Set<SourceListener> listeners = new ConcurrentSkipListSet<>();

  public MultiSourceFeeder(Source... sources) {
    this(Arrays.asList(sources));
  }

  public MultiSourceFeeder(Collection<Source> sources) {
    this.sources = sources;
  }

  @Override
  public void feed(ItemFactory itemFactory, ItemProcessor pipeline) {
    for (Source source : sources) {
      SingleSourceFeeder feeder = new SingleSourceFeeder(source);
      listeners.forEach(feeder::register);

      feeder.feed(itemFactory, pipeline);
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
