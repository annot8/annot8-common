package io.annot8.common.implementations.references;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.references.AnnotationReference;
import java.util.Optional;
import org.junit.jupiter.api.Test;

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

    private String contentName;
    private String annotationId;

    public TestAnnotationReference(String contentName, String annotationId) {
      this.contentName = contentName;
      this.annotationId = annotationId;
    }

    @Override
    public String getContentName() {
      return contentName;
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
