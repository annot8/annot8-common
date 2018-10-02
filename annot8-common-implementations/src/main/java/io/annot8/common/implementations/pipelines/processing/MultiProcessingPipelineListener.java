/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.processing;

import java.util.Set;
import java.util.function.Consumer;

import io.annot8.common.implementations.pipelines.common.ProcessingListener;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;

public class MultiProcessingPipelineListener implements ProcessingListener {

  private final Set<ProcessingListener> listeners;

  public MultiProcessingPipelineListener(Set<ProcessingListener> listeners) {
    this.listeners = listeners;
  }

  @Override
  public void onItemDiscarded(Item item) {
    forEach(l -> l.onItemDiscarded(item));
  }

  @Override
  public void onItemDone(Item item) {
    forEach(l -> l.onItemDone(item));
  }

  @Override
  public void onItemError(Item item, Processor processor) {
    forEach(l -> l.onItemError(item, processor));
  }

  @Override
  public void onStartItem(Item item) {
    forEach(l -> l.onStartItem(item));
  }

  @Override
  public void preProcess(Item item, Processor processor) {
    forEach(l -> l.preProcess(item, processor));
  }

  @Override
  public void postProcess(Item item, Processor processor, ProcessorResponse response) {
    forEach(l -> l.postProcess(item, processor, response));
  }

  protected void forEach(Consumer<ProcessingListener> consumer) {
    if (listeners != null) {
      listeners.forEach(consumer);
    }
  }
}
