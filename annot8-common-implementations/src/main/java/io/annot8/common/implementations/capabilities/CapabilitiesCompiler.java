package io.annot8.common.implementations.capabilities;

import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.capabilities.Capabilities.Builder;
import io.annot8.core.components.Annot8Component;
import java.util.function.Function;
import java.util.function.Supplier;

public class CapabilitiesCompiler {

  private final Supplier<Builder> builderSupplier;

  public CapabilitiesCompiler(Supplier<Builder> builderSupplier) {
    this.builderSupplier = builderSupplier;
  }

  public Capabilities compile(Annot8Component component) {
    Builder builder = builderSupplier.get();

    addAnnotatedCapabilities(builder, component.getClass());

    addGetCapabilities(builder, component);

    return builder.save();
  }

  private void addGetCapabilities(Builder builder, Annot8Component component) {
    component.buildCapabilities(builder);
  }

  private void addAnnotatedCapabilities(Builder builder, Class<?> clazz) {

    // Recurse through parents
    Class<?> superclass = clazz.getSuperclass();
    addAnnotatedCapabilities(builder, clazz);

    AnnotationBasedCapabilities capabilities = new AnnotationBasedCapabilities(clazz);
    builder.merge(capabilities);
  }

}
