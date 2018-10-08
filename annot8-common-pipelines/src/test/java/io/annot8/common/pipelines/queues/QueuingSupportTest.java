/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.data.BaseItemFactory;
import io.annot8.core.helpers.WithProcessItem;

@ExtendWith(MockitoExtension.class)
class QueuingSupportTest {

  @Mock ItemQueueReader queue;

  @Mock BaseItemFactory itemFactory;

  @Mock WithProcessItem processor;

  //  @Test
  //  void test() {
  //    throw new Annot8RuntimeException("Not implemented");
  //  }

  //  @Test
  //  void feedProcessesAllQueue() throws Annot8Exception {
  //
  //    Item item1 = mock(Item.class);
  //    Item item2 = mock(Item.class);
  //
  //    when(queue.hasItems()).thenReturn(true, true, false);
  //    when(queue.next()).thenReturn(item1, item2);
  //
  //    when(processor.process(item1)).thenReturn(ProcessorResponse.ok());
  //    // TODO: How to have multiple calls with different params in Mockito
  //    // when(processor.process(item2)).thenReturn(ProcessorResponse.ok());
  //
  //    final QueueFeeder feeder = new QueueFeeder(queue);
  //
  //    feeder.feed(processor);
  //
  //    verify(processor).process(item1);
  //    // verify(processor).process(item2);
  //
  //  }
  //
  //  @Test
  //  void setupContext() {
  //    Context delegate = mock(Context.class);
  //    Item item = mock(Item.class);
  //    when(itemFactory.create()).thenReturn(item);
  //    when(delegate.getItemFactory()).thenReturn(itemFactory);
  //    final QueueFeeder feeder = new QueueFeeder(queue);
  //
  //    final Context context = feeder.setupContext(delegate);
  //    final Item createdItem = context.getItemFactory().create();
  //
  //    assertThat(createdItem).isEqualTo(item);
  //    verify(queue).add(item);
  //  }
}
