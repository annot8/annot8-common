/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.pipelines;

import io.annot8.core.helpers.WithId;

public interface Pipeline extends WithId, AutoCloseable {

  void run();
}
