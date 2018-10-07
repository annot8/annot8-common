/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import io.annot8.core.data.Item;

public interface Merge extends AutoCloseable {

  boolean receive(Item item);
}
