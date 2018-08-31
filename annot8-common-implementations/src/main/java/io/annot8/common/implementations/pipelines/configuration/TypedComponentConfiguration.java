package io.annot8.common.implementations.pipelines.configuration;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.settings.Settings;
import java.util.Collection;

public class TypedComponentConfiguration<T extends Annot8Component> {

  private final Class<? extends T> componentClass;
  private final Collection<Settings> settings;

  public TypedComponentConfiguration(Class<? extends T> componentClass, Collection<Settings> settings) {
    this.componentClass = componentClass;
    this.settings = settings;
  }

  public Collection<Settings> getSettings() {
    return settings;
  }

  public Class<? extends T> getComponentClass() {
    return componentClass;
  }

}
