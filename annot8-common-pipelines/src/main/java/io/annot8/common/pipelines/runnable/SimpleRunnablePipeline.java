/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.runnable;

import io.annot8.common.pipelines.Pipeline;
import io.annot8.feeders.feeders.MultiSourceFeeder;
import io.annot8.queues.ItemQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.pipelines.simple.SimplePipe;
import io.annot8.configuration.ComponentConfigurer;
import io.annot8.configuration.ComponentHolder;
import io.annot8.configuration.ResourcesHolder;
import io.annot8.feeders.QueuePusher;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.context.Context;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class SimpleRunnablePipeline implements Pipeline, Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRunnablePipeline.class);

  private final String id;
  private final ResourcesHolder resourcesHolder;
  private final ComponentHolder<Source> sourceHolder;
  private final ComponentHolder<Processor> processorHolder;
  private final Optional<ItemQueue> queue;

  private Map<String, Resource> resources = new HashMap<>();
  private List<Processor> processors = new ArrayList<>();
  private List<Source> sources = new ArrayList<>();

  private final Set<ProcessingListener> listeners = new CopyOnWriteArraySet<>();
  private SimplePipe pipe;

  private ItemFactory itemFactory;
  private QueuePusher queuePusher;

  public SimpleRunnablePipeline(
      ResourcesHolder resourcesHolder,
      ComponentHolder<Source> sourceHolder,
      ComponentHolder<Processor> processorHolder,
      Optional<ItemQueue> queue) {
    this.queue = queue;
    this.id = UUID.randomUUID().toString();
    this.resourcesHolder = resourcesHolder;
    this.sourceHolder = sourceHolder;
    this.processorHolder = processorHolder;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {

    // Close old values
    close();

    Context globalContext = context;

    // Should we queue items?
    if (queue.isPresent()) {
      queuePusher = new QueuePusher(queue.get());
      globalContext = queuePusher.setupContext(context);
    }

    // Create a new
    itemFactory = globalContext.getItemFactory();
    Objects.requireNonNull(itemFactory);

    ComponentConfigurer componentConfigurer = new ComponentConfigurer(globalContext);
    resources = componentConfigurer.configureResources(resourcesHolder);
    processors = componentConfigurer.configureComponents(processorHolder);
    sources = componentConfigurer.configureComponents(sourceHolder);

    pipe = new SimplePipe(new MultiProcessingPipelineListener(this.listeners), processors);
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
  public void run() {
    MultiSourceFeeder feeder = new MultiSourceFeeder(sources);
    feeder.register(new ProcessQueueSourceListener(queuePusher, pipe));
    feeder.feed(itemFactory, pipe);
  }

  @Override
  public void close() {
    processors.forEach(Annot8Component::close);
    resources.values().forEach(Annot8Component::close);

    resources.clear();
    processors.clear();

    sources.forEach(Annot8Component::close);
    sources.clear();

    itemFactory = null;
    queuePusher = null;
    pipe = null;
  }
}
