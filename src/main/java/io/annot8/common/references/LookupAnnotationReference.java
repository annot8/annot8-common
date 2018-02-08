package io.annot8.common.references;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.references.AnnotationReference;
import java.util.Objects;
import java.util.Optional;

/**
 * A reference which will always retrieve the latest annotation from the appropriate annotation
 * store.
 *
 * Does not hold a reference to the group.
 */
public class LookupAnnotationReference implements AnnotationReference {

  private final Item item;

  private final String contentName;

  private final String annotationId;

  public LookupAnnotationReference(Item item, String contentName, String annotationId) {

    assert item != null;
    assert contentName != null;
    assert annotationId != null;

    this.item = item;
    this.contentName = contentName;
    this.annotationId = annotationId;
  }

  /**
   * Create an annotation reference for the annotation.
   */
  public static LookupAnnotationReference to(Item item, Annotation annotation) {
    assert annotation != null;
    return new LookupAnnotationReference(item, annotation.getContentName(), annotation.getId());
  }

  @Override
  public String getAnnotationId() {
    return annotationId;
  }

  @Override
  public String getContent() {
    return contentName;
  }

  @Override
  public boolean equals(Object o) {
    return sameReference(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contentName, annotationId);
  }

  @Override
  public Optional<Annotation> toAnnotation() {
    return item.getContent(contentName)
        .map(Content::getAnnotations)
        .flatMap(store -> store.getById(annotationId));
  }

}
