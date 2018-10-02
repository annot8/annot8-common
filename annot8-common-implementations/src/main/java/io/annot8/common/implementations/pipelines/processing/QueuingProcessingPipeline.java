/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.pipelines.common.ProcessingListener;
import io.annot8.common.implementations.pipelines.queues.ItemQueue;
import io.annot8.common.implementations.pipelines.queues.QueueProcessor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class QueuingProcessingPipeline implements ProcessingPipeline {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueuingProcessingPipeline.class);

  private final ProcessingPipeline pipeline;
  private final QueueProcessor processingQueue;

  public QueuingProcessingPipeline(ProcessingPipeline pipeline, ItemQueue queue) {
    this.pipeline = pipeline;
    processingQueue = new QueueProcessor(queue);
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {
    Context newContext = processingQueue.setupContext(context);
    this.pipeline.configure(newContext);
  }

  @Override
  public ProcessorResponse process(Item item) {
    try {
      final ProcessorResponse response = pipeline.process(item);

      // Also process all the queue which have jsut been added
      processingQueue.process(pipeline);

      return response;
    } catch (Annot8Exception e) {
      LOGGER.error("Failed to process item {}", item.getId(), e);
      return ProcessorResponse.processingError();
    }
  }

  @Override
  public void register(ProcessingListener listener) {
    pipeline.register(listener);
  }

  @Override
  public void unregister(ProcessingListener listener) {
    pipeline.unregister(listener);
  }

  @Override
  public String getId() {
    return pipeline.getId();
  }

  @Override
  public void close() {
    this.pipeline.close();
  }
}
