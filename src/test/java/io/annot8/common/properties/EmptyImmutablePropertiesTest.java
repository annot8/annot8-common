package io.annot8.common.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmptyImmutablePropertiesTest {

  @Test
  public void testEmptyImmutableProperties() {
    EmptyImmutableProperties eip = EmptyImmutableProperties.getInstance();
    Assertions.assertNotNull(eip);
    Assertions.assertEquals(eip, EmptyImmutableProperties.getInstance());
  }
}
