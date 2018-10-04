/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.runnable;

import io.annot8.queues.ItemQueue;
import io.annot8.common.pipelines.queues.SimpleItemQueue;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.configuration.ComponentHolder;
import io.annot8.configuration.ResourcesHolder;
import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.settings.Settings;

public abstract class AbstractRunnablePipelineBuilder implements RunnablePipelineBuilder {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(AbstractRunnablePipelineBuilder.class);

  // Use a linked hash map so the addition order = configuration order
  private final ComponentHolder<Processor> processorHolder = new ComponentHolder<>();
  private final ComponentHolder<Source> sourceHolder = new ComponentHolder<>();
  private final ResourcesHolder resourcesHolder = new ResourcesHolder();
  private ItemQueue queue = null;

  public RunnablePipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> configuration) {
    resourcesHolder.addResource(id, resource, configuration);
    return this;
  }

  public RunnablePipelineBuilder addProcessor(
      final Processor processor, final Collection<Settings> configuration) {
    processorHolder.add(processor, configuration);
    return this;
  }

  public RunnablePipelineBuilder addSource(
      final Source source, final Collection<Settings> configuration) {
    sourceHolder.add(source, configuration);
    return this;
  }

  public RunnablePipelineBuilder withQueue(final ItemQueue queue) {
    this.queue = queue;
    return this;
  }

  protected ComponentHolder<Processor> getProcessorHolder() {
    return processorHolder;
  }

  protected ResourcesHolder getResourcesHolder() {
    return resourcesHolder;
  }

  protected ComponentHolder<Source> getSourceHolder() {
    return sourceHolder;
  }

  protected ItemQueue getQueue() {
    // TODO: There's a difference between a Source push queue and the sub items queue
    // the latter should be optional
    if (queue == null) {
      LOGGER.warn(
          "Queue requires for Source ingest, non specified so using the an in-memory queue");
      queue = new SimpleItemQueue();
    }
    return queue;
  }
}
