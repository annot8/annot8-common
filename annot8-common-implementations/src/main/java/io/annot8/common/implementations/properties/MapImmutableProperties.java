package io.annot8.common.implementations.properties;

import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.properties.Properties;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of ImmutableProperties interface using an in-memory HashMap to store the
 * properties.
 */
public class MapImmutableProperties implements ImmutableProperties {

  private final Map<String, Object> properties;

  private MapImmutableProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  @Override
  public Map<String, Object> getAll() {
    return properties;
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [" + properties.entrySet().stream()
        .map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining(", ")) + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(properties);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Properties)) {
      return false;
    }

    Properties p = (Properties) o;
    return Objects.equals(properties, p.getAll());
  }

  /**
   * Builder class for InMemoryImmutableProperties
   */
  public static class Builder implements ImmutableProperties.Builder {

    private final Map<String, Object> properties = new HashMap<>();

    @Override
    public io.annot8.core.properties.ImmutableProperties.Builder from(Properties from) {
      properties.clear();
      from.getAll().forEach(properties::put);
      return this;
    }

    @Override
    public io.annot8.core.properties.ImmutableProperties.Builder withProperty(String key,
        Object value) {
      properties.put(key, value);
      return this;
    }

    @Override
    public Builder withoutProperty(String key, Object value) {
      Object val = properties.get(key);
      if (Objects.equals(value, val)) {
        properties.remove(key);
      }

      return this;
    }

    @Override
    public Builder withoutProperty(String key) {
      properties.remove(key);
      return this;
    }

    @Override
    public io.annot8.core.properties.ImmutableProperties.Builder withProperties(
        Properties properties) {
      properties.getAll().forEach(this.properties::put);
      return this;
    }

    @Override
    public ImmutableProperties save() {
      return new MapImmutableProperties(properties);
    }

  }
}