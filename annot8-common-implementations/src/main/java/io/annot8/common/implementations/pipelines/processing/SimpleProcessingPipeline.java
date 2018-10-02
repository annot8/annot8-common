/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.pipelines.common.ProcessingListener;
import io.annot8.common.implementations.pipelines.common.ProcessingPipe;
import io.annot8.common.implementations.pipelines.context.ComponentConfigurer;
import io.annot8.common.implementations.pipelines.context.ComponentHolder;
import io.annot8.common.implementations.pipelines.context.ResourcesHolder;
import io.annot8.common.implementations.pipelines.queues.ItemQueue;
import io.annot8.common.implementations.pipelines.queues.QueueProcessor;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.context.Context;
import io.annot8.core.data.Item;

public class SimpleProcessingPipeline implements ProcessingPipeline {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProcessingPipeline.class);

  private final String id;
  private final ResourcesHolder resourcesHolder;
  private final ComponentHolder<Processor> processorHolder;
  private final Optional<ItemQueue> queue;

  private Map<String, Resource> resources = new HashMap<>();
  private List<Processor> processors = new ArrayList<>();

  private final Set<ProcessingListener> listeners = new CopyOnWriteArraySet<>();
  private ProcessingPipe pipe;
  private QueueProcessor queueProcessor;

  public SimpleProcessingPipeline(
      ResourcesHolder resourcesHolder,
      ComponentHolder<Processor> processorHolder,
      Optional<ItemQueue> queue) {
    this.resourcesHolder = resourcesHolder;
    this.processorHolder = processorHolder;
    this.queue = queue;
    this.id = UUID.randomUUID().toString();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void configure(Context context) {

    // Close old values
    close();

    Context globalContext = context;

    // Should we queue items?
    if (queue.isPresent()) {
      queueProcessor = new QueueProcessor(queue.get());
      globalContext = queueProcessor.setupContext(context);
    }

    // Create a new

    ComponentConfigurer componentConfigurer = new ComponentConfigurer(globalContext);
    resources = componentConfigurer.configureResources(resourcesHolder);
    processors = componentConfigurer.configureComponents(processorHolder);

    pipe = new ProcessingPipe(new MultiProcessingPipelineListener(this.listeners), processors);
  }

  public ProcessorResponse process(final Item item) {

    final ProcessorResponse response = pipe.process(item);

    // Also process all the queue which have jsut been added
    if (queueProcessor != null) {
      queueProcessor.process(pipe);
    }

    return response;
  }

  @Override
  public void register(ProcessingListener listener) {
    this.listeners.add(listener);
  }

  @Override
  public void unregister(ProcessingListener listener) {
    this.listeners.remove(listener);
  }

  @Override
  public void close() {
    processors.forEach(Annot8Component::close);
    resources.values().forEach(Annot8Component::close);

    resources.clear();
    processors.clear();

    pipe = null;
    queueProcessor = null;
  }
}
