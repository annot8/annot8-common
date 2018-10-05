/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.pipeline;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import io.annot8.common.implementations.configuration.ComponentHolder;
import io.annot8.common.implementations.configuration.ResourcesHolder;
import io.annot8.common.pipelines.elements.Branch;
import io.annot8.common.pipelines.elements.Merge;
import io.annot8.common.pipelines.elements.Pipe;
import io.annot8.common.pipelines.elements.PipeBuilder;
import io.annot8.common.pipelines.elements.PipelineBuilder;
import io.annot8.common.pipelines.queues.ItemQueue;
import io.annot8.common.pipelines.queues.MemoryItemQueue;
import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.Annot8RuntimeException;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPipelineBuilder implements PipelineBuilder {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(AbstractPipelineBuilder.class);

  public static final String DEFAULT_PIPE = "DEFAULT";

  // Use a linked hash map so the addition order = configuration order
  private final ComponentHolder<Source> sourceHolder = new ComponentHolder<>();
  private final ResourcesHolder resourcesHolder = new ResourcesHolder();
  private final ListMultimap<String, Pipe> pipes = ArrayListMultimap.create();
  private final Multimap<Merge, String> merges = ArrayListMultimap.create();
  private final Multimap<Branch, String> branches = ArrayListMultimap.create();

  private ItemQueue queue = null;
  private String name = "anonymous";

  @Override
  public PipelineBuilder withName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public PipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> configuration) {
    resourcesHolder.addResource(id, resource, configuration);
    return this;
  }


  @Override
  public PipelineBuilder addSource(
      final Source source, final Collection<Settings> configuration) {
    sourceHolder.add(source, configuration);
    return this;
  }

  @Override
  public PipelineBuilder addPipe(PipeBuilder pipeBuilder) throws IncompleteException {
    return addPipe(DEFAULT_PIPE, pipeBuilder.build());
  }

  @Override
  public PipelineBuilder addPipe(String key, PipeBuilder pipe) throws IncompleteException {
    pipes.put(key, pipe.build());
    return this;
  }

  @Override
  public PipelineBuilder addPipe(Pipe pipe) {
    return addPipe(DEFAULT_PIPE, pipe);
  }

  @Override
  public PipelineBuilder addPipe(String key, Pipe pipe) {
    pipes.put(key, pipe);
    return this;
  }

  @Override
  public PipelineBuilder addBranch(Branch branch, String... keys) {
//    branches.putAll((branch, Arrays.asList(keys));
//    return this;
    throw new Annot8RuntimeException("No yet supported");
  }

  @Override
  public PipelineBuilder addMerge(Merge merge, String... keys) {
//    merges.putAll(merge, Arrays.asList(keys));
//    return this;
    throw new Annot8RuntimeException("No yet supported");
  }

  protected ResourcesHolder getResourcesHolder() {
    return resourcesHolder;
  }

  protected ComponentHolder<Source> getSourceHolder() {
    return sourceHolder;
  }

  protected ListMultimap<String, Pipe> getPipes() {
    return pipes;
  }

  protected String getName() {
    return name;
  }


  public PipelineBuilder withQueue(final ItemQueue queue) {
    this.queue = queue;
    return this;
  }

  protected ItemQueue getQueue() {
    if (queue == null) {
      LOGGER.warn(
          "Queue requires for Source ingest, non specified so using the an in-memory queue");
      queue = new MemoryItemQueue();
    }
    return queue;
  }
}
