package io.annot8.common.data.content;

import io.annot8.common.data.bounds.SpanBounds;
import java.util.Optional;
import java.util.stream.Stream;
import io.annot8.core.annotations.Annotation;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.data.Content;

/**
 * Represents plain text content having data of type string.
 *
 * This could the content of a document, a cell in a database, or anything else which is actual
 * text.
 *
 * Do not use this for non plain/text (eg HTML).
 */
public interface Text extends Content<String> {

  /**
   * Get the text under the annotation, if the annotation's bounds are of a supported type.
   */
  default Optional<String> getText(Annotation annotation) {
    Bounds bounds = annotation.getBounds();
    return bounds.getData(this, String.class);
  }

  default Stream<Annotation> getBetween(int begin, int end) {
    return getAnnotations()
        .getByBounds(SpanBounds.class)
        .filter(a -> a.getBounds(SpanBounds.class).get().isWithin(begin, end));

  }
}
