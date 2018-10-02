package io.annot8.common.implementations.delegates;

import io.annot8.core.components.Resource;
import io.annot8.core.context.Context;
import io.annot8.core.data.ItemFactory;
import io.annot8.core.settings.Settings;
import java.util.Optional;
import java.util.stream.Stream;

public class DelegateContext implements Context {

  private final Context delegate;

  public DelegateContext(Context delegate) {

    this.delegate = delegate;
  }

  @Override
  public Stream<Settings> getSettings() {
    return delegate.getSettings();
  }

  @Override
  public <T extends Settings> Optional<T> getSettings(Class<T> clazz) {
    return delegate.getSettings(clazz);
  }

  @Override
  public <T extends Resource> Optional<T> getResource(String key, Class<T> clazz) {
    return delegate.getResource(key, clazz);
  }

  @Override
  public Stream<String> getResourceKeys() {
    return delegate.getResourceKeys();
  }

  @Override
  public Stream<String> getResourceKeys(Class<? extends Resource> clazz) {
    return delegate.getResourceKeys(clazz);
  }

  @Override
  public <T extends Resource> Optional<T> getResource(Class<T> clazz) {
    return delegate.getResource(clazz);
  }

  @Override
  public <T extends Resource> Stream<T> getResources(Class<T> clazz) {
    return delegate.getResources(clazz);
  }

  @Override
  public ItemFactory getItemFactory() {
    return delegate.getItemFactory();
  }
}
