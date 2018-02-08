package io.annot8.common.bounds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoBoundsTest {

  @Test
  public void testNoBounds() {
    NoBounds nb = NoBounds.getInstance();
    Assertions.assertNotNull(nb);
    assertEquals(nb, NoBounds.getInstance());
    assertNotNull(nb.toString());

    assertTrue(nb.isValid(null));
    assertFalse(nb.getData(null, null).isPresent());
  }
}
