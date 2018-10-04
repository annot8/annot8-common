package io.annot8.pipelines.elements;

import io.annot8.core.data.Item;

public interface Merge extends AutoCloseable {

  boolean receive(Item item);

}
