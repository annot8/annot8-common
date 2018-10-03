/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.common;

import io.annot8.core.helpers.WithProcessItem;
import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.pipelines.processing.SimpleProcessingPipeline;
import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.components.responses.ProcessorResponse.Status;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

public class ProcessingPipe implements WithProcessItem {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProcessingPipeline.class);

  private final ProcessingListener listener;
  private final List<Processor> processors;

  public ProcessingPipe(ProcessingListener listener, List<Processor> processors) {
    this.listener = listener;
    this.processors = processors;
  }

  @Override
  public ProcessorResponse process(final Item item) {

    fireListeners(l -> l.onStartItem(item));

    for (final Processor processor : processors) {

      fireListeners(l -> l.preProcess(item, processor));

      try {
        final ProcessorResponse response = processor.process(item);
        fireListeners(l -> l.postProcess(item, processor, response));

        final Status status = response.getStatus();
        if (status == Status.OK) {
          if (item.isDiscarded()) {
            LOGGER.warn("Item discarded, stopping processing");
            fireListeners(l -> l.onItemDiscarded(item));
            return response;
          }

        } else if (status == Status.PROCESSOR_ERROR) {
          LOGGER.error("RunnablePipeline problem, exiting");
          return response;
        } else if (status == Status.ITEM_ERROR) {
          fireListeners(l -> l.onItemError(item, processor));
          LOGGER.error("Item problem, skipping rest of pipeline");
          return response;
        }

      } catch (final Annot8Exception e) {
        LOGGER.error(
            "Failed to process data item with processor {}", processor.getClass().getName(), e);
      }
    }

    return ProcessorResponse.ok();
  }

  protected void fireListeners(Consumer<ProcessingListener> event) {
    if (listener != null) {
      event.accept(listener);
    }
  }
}
