package io.annot8.common.implementations.capabilities;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.settings.EmptySettings;
import io.annot8.core.settings.Settings;
import io.annot8.core.settings.SettingsClass;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Collates all settings class for components based on the SettingsClass annotation.
 */
public class SettingsCompiler {

  /**
   * Collate settings for a component into a single map
   *
   * @param component the component to consider
   * @return the map of parent class to the settings it declares
   */
  public Map<Class<?>, Class<? extends Settings>> compile(Annot8Component component) {
    Map<Class<?>, Class<? extends Settings>> classes = new HashMap<>();

    addAnnotatedSettings(classes, component.getClass());

    return Collections.unmodifiableMap(classes);
  }

  /**
   * Collate seetings for a component (and its parent classes) into a set
   *
   * @param component the component to consider
   * @return the set settings declared
   */
  public Set<Class<? extends Settings>> compileAsSet(Annot8Component component) {

    Collection<Class<? extends Settings>> values = compile(component).values();

    if (values.isEmpty()) {
      return Collections.emptySet();
    } else {
      return Collections.unmodifiableSet(new HashSet<>(values));
    }
  }

  protected void addAnnotatedSettings(Map<Class<?>, Class<? extends Settings>> classes,
      Class<?> clazz) {
    // Recurse through parents
    Class<?> superclass = clazz.getSuperclass();
    addAnnotatedSettings(classes, superclass);

    SettingsClass[] annotations = clazz.getAnnotationsByType(SettingsClass.class);

    Arrays.stream(annotations)
        // Discard anything which is effectively 'no settings'
        .filter(a -> !Settings.class.equals(a.value()) && !EmptySettings.class.equals(a.value()))
        .forEach(a -> classes.put(clazz, a.value()));
  }

}
