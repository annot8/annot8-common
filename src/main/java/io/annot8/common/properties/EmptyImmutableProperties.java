package io.annot8.common.properties;

import io.annot8.core.properties.ImmutableProperties;
import java.util.Collections;
import java.util.Map;

/**
 * Empty implementation of ImmutableProperties interface
 */
public final class EmptyImmutableProperties implements ImmutableProperties {

  private static final EmptyImmutableProperties INSTANCE = new EmptyImmutableProperties();

  private EmptyImmutableProperties() {
    //Empty constructor
  }

  public static final EmptyImmutableProperties getInstance() {
    return INSTANCE;
  }

  @Override
  public Map<String, Object> getAll() {
    return Collections.emptyMap();
  }

  @Override
  public String toString() {
    return "EmptyImmutableProperties";
  }
}
