package io.annot8.common.implementations.content;

import io.annot8.common.implementations.factories.ContentBuilderFactory;
import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.Content;

public abstract class AbstractContentBuilderFactory<D, C extends Content<D>>  implements ContentBuilderFactory<D, C> {

    private final Class<D> dataClass;
    private final Class<C> contentClass;
  private final AnnotationStoreFactory annotationStoreFactory;

  protected AbstractContentBuilderFactory(Class<D> dataClass, Class<C> contentClass, AnnotationStoreFactory annotationStoreFactory) {
      this.dataClass = dataClass;
      this.contentClass = contentClass;
    this.annotationStoreFactory = annotationStoreFactory;
  }

  protected AnnotationStoreFactory getAnnotationStoreFactory() {
    return annotationStoreFactory;
  }

  @Override
    public Class<D> getDataClass() {
      return dataClass;
    }

    @Override
    public Class<C> getContentClass() {
      return contentClass;
    }

}