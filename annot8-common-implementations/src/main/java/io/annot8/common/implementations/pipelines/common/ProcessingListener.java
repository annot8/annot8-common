/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.common;

import io.annot8.core.components.Processor;
import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;

public interface ProcessingListener {

  default void onStartItem(Item item) {}

  default void preProcess(Item item, Processor processor) {}

  default void postProcess(Item item, Processor processor, ProcessorResponse response) {}

  default void onItemDiscarded(Item item) {}

  default void onItemDone(Item item) {}

  default void onItemError(Item item, Processor processor) {}
}
