package io.annot8.common.implementations.pipelines.configuration;

import io.annot8.core.settings.Settings;
import java.util.Set;

public interface ComponentConfiguration {

  String getName();

  String getComponent();

  Set<Settings> getSettings();

}
