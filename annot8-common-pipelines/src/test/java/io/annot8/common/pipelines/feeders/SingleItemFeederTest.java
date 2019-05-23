/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.feeders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.events.source.SourceEmptyEvent;
import io.annot8.common.pipelines.events.source.SourceReadEvent;
import io.annot8.common.pipelines.listeners.SourceListener;
import io.annot8.core.components.Source;
import io.annot8.core.components.responses.SourceResponse;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.helpers.WithProcessItem;

@ExtendWith(MockitoExtension.class)
class SingleItemFeederTest {

  @Mock Source source;

  @Mock ItemFactory itemFactory;

  @Mock WithProcessItem processor;

  @Test
  void feed() {
    when(source.read(itemFactory))
        .thenReturn(SourceResponse.ok())
        .thenReturn(SourceResponse.done());

    SingleItemFeeder feeder = new SingleItemFeeder(itemFactory, source);

    assertFalse(feeder.feed(processor)); // Returns false because we return DONE rather than EMPTY

    verify(source, Mockito.times(2)).read(itemFactory);

    feeder.close();
  }

  @Test
  void register() {
    when(source.read(itemFactory))
        .thenReturn(SourceResponse.ok())
        .thenReturn(SourceResponse.empty());

    final SourceListener listener = mock(SourceListener.class);

    SingleItemFeeder feeder = new SingleItemFeeder(itemFactory, source);

    feeder.register(listener);

    assertTrue(feeder.feed(processor)); // Returns true because we return EMPTY

    verify(listener).onSourceEvent(any(SourceReadEvent.class));
    verify(listener).onSourceEvent(any(SourceEmptyEvent.class));

    feeder.close();
  }
}
