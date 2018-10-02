/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.context;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.annot8.core.components.Resource;
import io.annot8.core.context.Context;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.exceptions.Annot8RuntimeException;
import io.annot8.core.settings.Settings;

// TODO: this could be cleans up with a helper method for stream and optional

public class MergedContext implements Context {

  private final List<Context> contexts;

  public MergedContext(Context... contexts) {
    this.contexts = Arrays.asList(contexts);
  }

  @Override
  public Stream<Settings> getSettings() {
    return contexts.stream().flatMap(c -> c.getSettings());
  }

  @Override
  public <T extends Settings> Optional<T> getSettings(Class<T> clazz) {
    return contexts
        .stream()
        .map(c -> c.getSettings(clazz))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst();
  }

  @Override
  public <T extends Resource> Optional<T> getResource(String key, Class<T> clazz) {
    return contexts
        .stream()
        .map(c -> c.getResource(key, clazz))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst();
  }

  @Override
  public Stream<String> getResourceKeys() {
    return contexts.stream().flatMap(c -> c.getResourceKeys());
  }

  @Override
  public Stream<String> getResourceKeys(Class<? extends Resource> clazz) {
    return contexts.stream().flatMap(c -> c.getResourceKeys(clazz));
  }

  @Override
  public <T extends Resource> Optional<T> getResource(Class<T> clazz) {
    return contexts
        .stream()
        .map(c -> c.getResource(clazz))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst();
  }

  @Override
  public <T extends Resource> Stream<T> getResources(Class<T> clazz) {
    return contexts.stream().flatMap(c -> c.getResources(clazz));
  }

  @Override
  public ItemFactory getItemFactory() {
    return contexts
        .stream()
        .map(c -> c.getItemFactory())
        .filter(Objects::nonNull)
        .findFirst()
        .orElseThrow(() -> new Annot8RuntimeException("Missing ItemFactory"));
  }
}
