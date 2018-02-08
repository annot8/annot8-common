package io.annot8.common.references;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.annot8.core.annotations.Annotation;
import org.junit.jupiter.api.Test;

class WrappedAnnotationReferenceTest {

  @Test
  void newReference() {
    Annotation annotation = mock(Annotation.class);
    when(annotation.getContentName()).thenReturn("content");
    when(annotation.getId()).thenReturn("2");

    WrappedAnnotationReference reference = new WrappedAnnotationReference(annotation);

    assertEquals("content", reference.getContent());
    verify(annotation, only()).getContentName();

    clearInvocations(annotation);

    assertEquals("2", reference.getAnnotationId());
    verify(annotation, only()).getId();

    assertSame(annotation, reference.toAnnotation().get());
  }


}