package io.annot8.common.references;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.data.Item;
import java.util.Optional;

/**
 * A reference to an annotation.
 *
 * As annotations can change (though their id and content stays the same) we can't simply hold
 * Annotation in Java objects. Instead we hold a reference to them (based on content and annotation
 * ids).
 *
 * Since an content or annotation may be deleted, we do not know if the annotation still exists when
 * we call toAnnotation.
 */
@FunctionalInterface
public interface AnnotationReference {

  Optional<Annotation> toAnnotation();

}
