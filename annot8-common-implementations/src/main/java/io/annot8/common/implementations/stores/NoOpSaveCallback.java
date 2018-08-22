package io.annot8.common.implementations.stores;

/**
 * Passes through the object without modification.
 *
 *
 * This is useful if your require a SaveCallback, but don't need to do anything with the object.
 */
public final class NoOpSaveCallback<T> implements SaveCallback<T, T> {

  @Override
  public T save(T item) {
    return item;
  }

}
