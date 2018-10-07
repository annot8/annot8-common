/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.stores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NoOpSaveCallbackTest {

  @Test
  void save() {
    Object o = new Object();
    final Object saved = new NoOpSaveCallback<Object>().save(o);
    assertSame(o, saved);
  }
}
