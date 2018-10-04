package io.annot8.pipelines.elements;

import io.annot8.core.components.Resource;
import io.annot8.core.components.Source;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;
import java.util.Arrays;
import java.util.Collection;

public interface PipelineBuilder {
  PipelineBuilder withName(String name);

  default PipelineBuilder addResource(
      final String id, final Resource resource, final Settings... settings) {
    return addResource(id, resource, Arrays.asList(settings));
  }

  PipelineBuilder addResource(
      final String id, final Resource resource, final Collection<Settings> settings);


  default PipelineBuilder addSource(final Source source, final Settings... settings) {
    addSource(source, Arrays.asList(settings));
    return this;
  }

  PipelineBuilder addSource(final Source source, final Collection<Settings> settings);




  Pipeline build() throws IncompleteException;
}
