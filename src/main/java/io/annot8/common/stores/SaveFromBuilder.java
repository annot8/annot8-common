package io.annot8.common.stores;

@FunctionalInterface
public interface SaveFromBuilder<T, R> {

  R save(T item);
}
