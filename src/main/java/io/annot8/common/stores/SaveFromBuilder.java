package io.annot8.common.stores;

/**
 * Call back to save an item
 *
 * @param <T> the return type of save
 * @param <R> the item to save
 */
@FunctionalInterface
// TODO: Rename to something better (it is used in builders, but could just be Saver/SaveCallback)
public interface SaveFromBuilder<T, R> {

  R save(T item);
}
