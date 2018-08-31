package io.annot8.common.implementations.pipelines.configuration;

import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.settings.Settings;
import java.util.Collection;

public interface PipelineComponentConfiguration {

  String getComponentClassName();

  Collection<Settings> getComponentSettings();

  interface Builder {

    PipelineComponentConfiguration.Builder withComponentClassName(String componentClassName);

    PipelineComponentConfiguration.Builder withComponentSettings(Settings settings);

    PipelineComponentConfiguration create() throws IncompleteException;

  }

}
