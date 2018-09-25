/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

/**
 * Call back to save an item
 *
 * @param <T> the return type of save
 * @param <R> the item to save
 */
@FunctionalInterface
public interface SaveCallback<T, R> {

  /**
   * Save the parameter.
   *
   * <p>Should not return null (throw exception)
   */
  R save(T item);
}
