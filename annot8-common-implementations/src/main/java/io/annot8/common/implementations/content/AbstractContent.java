package io.annot8.common.implementations.content;

import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import java.util.function.Supplier;

public abstract class AbstractContent<D> implements Content<D> {

  private final Class<D> dataClass;
  private final Class<? extends Content<D>> contentClass;

  private final String id;
  private final String name;
  private final AnnotationStore annotations;
  private final ImmutableProperties properties;
  private final Supplier<D> data;

  protected AbstractContent(Class<D> dataClass, Class<? extends Content<D>> contentClass,  AnnotationStoreFactory annotationStoreFactory,String id, String name,
      ImmutableProperties properties, Supplier<D> data) {
    this.dataClass = dataClass;
    this.contentClass = contentClass;
    this.id = id;
    this.name = name;
    this.properties = properties;
    this.data = data;

    this.annotations = annotationStoreFactory.create(this);
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public D getData() {
    return data.get();
  }

  @Override
  public Class<D> getDataClass() {
    return dataClass;
  }

  @Override
  public Class<? extends Content<D>> getContentClass() {
    return contentClass;
  }

  @Override
  public AnnotationStore getAnnotations() {
    return annotations;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }

}
