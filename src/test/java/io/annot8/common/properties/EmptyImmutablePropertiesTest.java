package io.annot8.common.properties;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmptyImmutablePropertiesTest {

  @Test
  void getAll() {
    assertTrue(EmptyImmutableProperties.getInstance().getAll().isEmpty());

  }

  @Test
  void testToString() {
    String s = EmptyImmutableProperties.getInstance().toString();
    assertNotNull(s);
    assertNotEquals("", s);

  }

  @Test
  public void testEmptyImmutableProperties() {
    EmptyImmutableProperties eip = EmptyImmutableProperties.getInstance();
    assertNotNull(eip);
    Assertions.assertEquals(eip, EmptyImmutableProperties.getInstance());
  }
}
