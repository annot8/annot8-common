package io.annot8.common.data.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.bounds.Bounds;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextTest {

  private Annotation annotation;
  private Bounds bounds;
  private Text text;

  @BeforeEach
  void beforeEach() {
    // Mocking interface under test
    text = mock(Text.class);
    when(text.getText(any(Annotation.class))).thenCallRealMethod();

    when(text.getData()).thenReturn("Test data");

    bounds = mock(Bounds.class);
    annotation = mock(Annotation.class);
    when(annotation.getBounds()).thenReturn(bounds);
  }

  @Test
  void testTextTextNonEmpty() {

    when(bounds.getData(eq(text), eq(String.class))).thenReturn(Optional.of("covered"));

    Optional<String> output = text.getText(annotation);
    assertEquals("covered", output.get());
  }

  @Test
  void testTextTextEmpty() {

    when(bounds.getData(eq(text), eq(String.class))).thenReturn(Optional.empty());

    Optional<String> output = text.getText(annotation);
    assertFalse(output.isPresent());
  }
}