package io.annot8.common.implementations.annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.properties.ImmutableProperties;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class AbstractAnnotationTest {

  @Test
  public void testBasicEquals() {
    TestAnnotation annotation =
        new TestAnnotation("id", "type", "content", Collections.emptyMap(), null);
    TestAnnotation different =
        new TestAnnotation("diffId", "diffType", "diffContentName", Collections.emptyMap(), null);

    assertTrue(annotation.equals(annotation));
    assertFalse(annotation.equals(null));
    assertFalse(annotation.equals(new Object()));
  }

  @Test
  public void testEqualsWithFields() {
    TestAnnotation annotation =
        new TestAnnotation("id", "type", "content", Collections.singletonMap("key", "value"), null);
    TestAnnotation same =
        new TestAnnotation("id", "type", "content", Collections.singletonMap("key", "value"), null);

    assertTrue(annotation.equals(same));
    assertEquals(annotation.hashCode(), same.hashCode());

    TestAnnotation differentMap = new TestAnnotation("id", "type", "content",
        Collections.singletonMap("key", "diffValue"), null);
    assertFalse(annotation.equals(differentMap));

    TestAnnotation differentId = new TestAnnotation("diffId", "type", "content",
        Collections.singletonMap("key", "value"), null);
    assertFalse(annotation.equals(differentId));

    TestAnnotation differentType = new TestAnnotation("id", "diffType", "content",
        Collections.singletonMap("key", "value"), null);
    assertFalse(annotation.equals(differentType));

    TestAnnotation differentContent = new TestAnnotation("id", "type", "diffContent",
        Collections.singletonMap("key", "value"), null);
    assertFalse(annotation.equals(differentContent));
  }

  private class TestAnnotation extends AbstractAnnotation {

    private String id;
    private String type;
    private Map<String, Object> properties;
    private String contentId;
    private Bounds bounds;

    public TestAnnotation(String id, String type, String contentId,
        Map<String, Object> properties, Bounds bounds) {
      this.id = id;
      this.type = type;
      this.contentId = contentId;
      this.properties = properties;
      this.bounds = bounds;
    }

    @Override
    public Bounds getBounds() {
      return bounds;
    }

    @Override
    public String getContentId() {
      return contentId;
    }

    @Override
    public String getId() {
      return id;
    }

    @Override
    public String getType() {
      return type;
    }

    @Override
    public ImmutableProperties getProperties() {
      return new ImmutableProperties() {
        @Override
        public Map<String, Object> getAll() {
          return properties;
        }
      };
    }
  }

}
