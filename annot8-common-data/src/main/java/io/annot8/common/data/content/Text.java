package io.annot8.common.data.content;

import io.annot8.common.data.bounds.SpanBounds;
import io.annot8.core.annotations.Annotation;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.data.Content;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Represents plain text content having data of type string.
 *
 * This could the content of a document, a cell in a database, or anything else which is actual
 * text.
 *
 * Do not use this for non plain/text (eg HTML), as these have structure to be considered.
 */
public interface Text extends Content<String> {

  /**
   * Get the text under the annotation, if the annotation's bounds are of a supported type.
   *
   * @return the text under the annotation
   */
  default Optional<String> getText(Annotation annotation) {
    Bounds bounds = annotation.getBounds();
    return bounds.getData(this, String.class);
  }


  /**
   * Get annotations between the offsets
   *
   * @param begin the start offset
   * @param end the end offset
   * @return annotations
   */
  default Stream<Annotation> getBetween(int begin, int end) {
    return filterAnnotations(SpanBounds.class, s -> s.isWithin(begin, end));
  }

  /**
   * Get annotations after the offset
   *
   * @param offset offset
   * @return annotations
   */
  default Stream<Annotation> getAfter(int offset) {
    return filterAnnotations(SpanBounds.class, s -> s.isAfter(offset));
  }

  /**
   * Get annotations before the offset
   *
   * @param offset offset
   * @return annotations
   */
  default Stream<Annotation> getBefote(int offset) {
    return filterAnnotations(SpanBounds.class, s -> s.isBefore(offset));
  }

  private <T extends Bounds> Stream<Annotation> filterAnnotations(Class<T> boundsClass, Predicate<T> predicate) {
    return getAnnotations()
        .getByBounds(boundsClass)
        .filter(a -> predicate.test(a.getBounds(boundsClass).get()));
  }
}