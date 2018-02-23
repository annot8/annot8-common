package io.annot8.common.stores;

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
   * Should not return null (throw exception)
   */
  R save(T item);
}
