package io.annot8.common.implementations.stores;

/**
 * Passes through the object without modification.
 */
public final class NoOpSaveCallback<T> implements SaveCallback<T, T> {

  @Override
  public T save(T item) {
    return item;
  }

}
