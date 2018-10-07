/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.implementations.configuration.ComponentConfigurer;
import io.annot8.common.implementations.configuration.ComponentHolder;
import io.annot8.common.implementations.configuration.ResourcesHolder;
import io.annot8.common.pipelines.base.AbstractTask;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.feeders.MultiItemFeeder;
import io.annot8.common.pipelines.feeders.QueueFeeder;
import io.annot8.common.pipelines.queues.ItemQueue;
import io.annot8.common.pipelines.queues.ProcessQueueSourceListener;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.context.Context;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public class SimplePipeline extends AbstractTask implements Pipeline {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimplePipeline.class);

  private final ResourcesHolder resourcesHolder;
  private final ComponentHolder<Source> sourceHolder;
  private final List<Pipe> pipes;
  private final Optional<ItemQueue> queue;

  private Map<String, Resource> resources;
  private List<Source> sources;

  private Pipe pipe;
  private ItemFactory itemFactory;
  private QueueFeeder queuePusher;

  public SimplePipeline(
      String name,
      ResourcesHolder resourcesHolder,
      ComponentHolder<Source> sourceHolder,
      List<Pipe> pipes,
      Optional<ItemQueue> queue) {
    super(name);
    this.pipes = pipes;
    this.queue = queue;
    this.resourcesHolder = resourcesHolder;
    this.sourceHolder = sourceHolder;
  }

  @Override
  public void configure(Context context)
      throws BadConfigurationException, MissingResourceException {

    // Close old values
    close();

    Context globalContext = context;

    // Should we queue items?
    if (queue.isPresent()) {
      queuePusher = new QueueFeeder(queue.get());
      globalContext = queuePusher.setupContext(context);
    }

    // Create a new
    itemFactory = globalContext.getItemFactory();
    Objects.requireNonNull(itemFactory);

    ComponentConfigurer componentConfigurer = new ComponentConfigurer(globalContext);
    resources = componentConfigurer.configureResources(resourcesHolder);
    sources = componentConfigurer.configureComponents(sourceHolder);

    // Configure all our pipes (under a single pipe)
    pipe = new MultiPipe(getName(), pipes);
    componentConfigurer.configureComponent(pipe, Collections.emptyList());
  }

  @Override
  protected void perform() {
    MultiItemFeeder feeder = new MultiItemFeeder(itemFactory, sources);
    feeder.register(new ProcessQueueSourceListener(queuePusher, pipe));
    feeder.feed(pipe);
  }

  @Override
  public void close() {
    if (pipe != null) {
      pipe.close();
    }

    if (sources != null) {
      sources.forEach(Annot8Component::close);
      sources.clear();
    }

    if (resources != null) {
      resources.values().forEach(Annot8Component::close);
      resources.clear();
    }

    itemFactory = null;
    queuePusher = null;
    pipe = null;
  }
}
