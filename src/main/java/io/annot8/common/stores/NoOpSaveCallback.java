package io.annot8.common.stores;

/**
 * Passes through the object without modification.
 */
public class NoOpSaveCallback<T> implements SaveCallback<T, T> {

  @Override
  public T save(T item) {
    return item;
  }

}
