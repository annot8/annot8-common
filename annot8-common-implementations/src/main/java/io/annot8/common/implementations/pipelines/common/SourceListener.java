/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines.common;

import io.annot8.core.components.Source;

public interface SourceListener {

  default void onSourceRead(Source source) {}

  default void onSourceError(Source source) {}

  default void onSourceDone(Source source) {}

  default void onSourceEmpty(Source source) {}
}
