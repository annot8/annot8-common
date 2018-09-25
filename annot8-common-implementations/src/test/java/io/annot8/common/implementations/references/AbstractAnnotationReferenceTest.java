/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.references;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.references.AnnotationReference;

public class AbstractAnnotationReferenceTest {

  @Test
  public void testEquals() {
    AnnotationReference ref = new TestAnnotationReference("content", "annotationID");
    AnnotationReference same = new TestAnnotationReference("content", "annotationID");
    AnnotationReference diff = new TestAnnotationReference("diffContent", "diffId");

    assertTrue(ref.equals(ref));
    assertTrue(ref.equals(same));
    assertEquals(ref.hashCode(), same.hashCode());
    assertFalse(same.equals(diff));
    assertFalse(ref.equals(null));
    assertFalse(ref.equals(new Object()));
  }

  private class TestAnnotationReference extends AbstractAnnotationReference {

    private String contentId;
    private String annotationId;

    public TestAnnotationReference(String contentId, String annotationId) {
      this.contentId = contentId;
      this.annotationId = annotationId;
    }

    @Override
    public String getContentId() {
      return contentId;
    }

    @Override
    public String getAnnotationId() {
      return annotationId;
    }

    @Override
    public Optional<Annotation> toAnnotation() {
      throw new UnsupportedOperationException("Not supported in test implementation");
    }
  }
}
