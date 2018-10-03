/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.processing;

import io.annot8.common.pipelines.configuration.ComponentHolder;
import io.annot8.common.pipelines.configuration.ResourcesHolder;
import io.annot8.common.pipelines.queues.ItemQueue;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.core.components.Processor;
import io.annot8.core.components.Resource;
import io.annot8.core.settings.Settings;

public abstract class AbstractProcessingPipelineBuilder implements ProcessingPipelineBuilder {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(AbstractProcessingPipelineBuilder.class);

  // Use a linked hash map so the addition order = configuration order
  private final ComponentHolder<Processor> processorHolder = new ComponentHolder<>();

  private final ResourcesHolder resourcesHolder = new ResourcesHolder();
  private ItemQueue queue = null;

  public ProcessingPipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> configuration) {
    resourcesHolder.addResource(id, resource, configuration);
    return this;
  }

  public ProcessingPipelineBuilder addProcessor(
      final Processor processor, final Collection<Settings> configuration) {
    processorHolder.add(processor, configuration);
    return this;
  }

  public ProcessingPipelineBuilder withQueue(final ItemQueue queue) {
    this.queue = queue;
    return this;
  }

  protected ComponentHolder<Processor> getProcessorHolder() {
    return processorHolder;
  }

  protected ResourcesHolder getResourcesHolder() {
    return resourcesHolder;
  }

  protected ItemQueue getQueue() {
    return queue;
  }
}
