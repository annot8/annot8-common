/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

/**
 * Passes through the object without modification.
 *
 * <p>This is useful if your require a SaveCallback, but don't need to do anything with the object.
 */
public final class NoOpSaveCallback<T> implements SaveCallback<T, T> {

  @Override
  public T save(T item) {
    return item;
  }
}
