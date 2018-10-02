/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.common;

import io.annot8.core.components.responses.ProcessorResponse;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;

// TODO: Should be in core
public interface ItemProcessor {
  ProcessorResponse process(final Item item) throws Annot8Exception;
}
