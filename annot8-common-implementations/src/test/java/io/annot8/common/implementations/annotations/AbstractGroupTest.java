package io.annot8.common.implementations.annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import io.annot8.core.annotations.Annotation;
import io.annot8.core.annotations.Group;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.references.AnnotationReference;

public class AbstractGroupTest {

  @Test
  public void testBasicEquals() {
    String groupId = "groupId";
    String groupType = "groupType";
    Group group = new TestGroup(groupId, groupType, Collections.emptyMap(), Collections.emptyMap());
    Group other = new TestGroup(groupId, groupType, Collections.emptyMap(), Collections.emptyMap());

    assertTrue(group.equals(group));
    assertTrue(group.equals(other));
    assertEquals(group.hashCode(), other.hashCode());
    assertFalse(group.equals(null));
    assertFalse(group.equals(new Object()));
  }

  @Test
  public void testEqualsWithProperties() {
    String groupId = "groupId";
    String groupType = "groupType";
    String key = "key";
    String value = "value";
    Group group = new TestGroup(groupId, groupType, Collections.emptyMap(),
        Collections.singletonMap(key, value));
    Group other = new TestGroup(groupId, groupType, Collections.emptyMap(),
        Collections.singletonMap(key, value));

    assertTrue(group.equals(other));
    assertEquals(group.hashCode(), other.hashCode());

    Group differentProperties = new TestGroup(groupId, groupType, Collections.emptyMap(),
        Collections.singletonMap(key, "diffValue"));

    assertFalse(group.equals(differentProperties));
  }

  @Test
  public void testEqualsWithReferences() {
    String groupId = "groupId";
    String groupType = "groupType";
    String annotationId = "annoId";
    String contentName = "contentName";
    String role = "role";
    AnnotationReference reference = getAnnotationReference(annotationId, contentName);

    Group group = new TestGroup(groupId, groupType,
        Collections.singletonMap(role, Collections.singletonList(reference)),
        Collections.emptyMap());
    Group other = new TestGroup(groupId, groupType,
        Collections.singletonMap(role, Collections.singletonList(reference)),
        Collections.emptyMap());

    assertTrue(group.equals(other));

    AnnotationReference diffReference = getAnnotationReference("diffId", "diffName");

    Group differentReference = new TestGroup(groupId, groupType,
        Collections.singletonMap(role, Collections.singletonList(diffReference)),
        Collections.emptyMap());

    assertFalse(group.equals(differentReference));

    Group differentRole = new TestGroup(groupId, groupType,
        Collections.singletonMap("diffRole", Collections.singletonList(reference)),
        Collections.emptyMap());

    assertFalse(group.equals(differentRole));
  }

  private AnnotationReference getAnnotationReference(String annotationId, String contentName) {
    return new AnnotationReference() {

      @Override
      public Optional<Annotation> toAnnotation() {
        return null;
      }

      @Override
      public String getContentName() {
        return contentName;
      }

      @Override
      public String getAnnotationId() {
        return annotationId;
      }
    };
  }

  private class TestGroup extends AbstractGroup {

    private String id;
    private String type;
    private Map<String, Collection<AnnotationReference>> refs;
    private Map<String, Object> properties;

    public TestGroup(String id, String type, Map<String, Collection<AnnotationReference>> refs,
        Map<String, Object> properties) {
      this.id = id;
      this.type = type;
      this.refs = refs;
      this.properties = properties;
    }

    @Override
    public Map<String, Stream<AnnotationReference>> getReferences() {
      return refs.entrySet().stream()
          .collect(Collectors.toMap(Entry::getKey, v -> v.getValue().stream()));
    }

    @Override
    public Optional<String> getRole(Annotation annotation) {
      throw new UnsupportedOperationException("Test class does not support this operation");
    }

    @Override
    public boolean containsAnnotation(Annotation annotation) {
      throw new UnsupportedOperationException("Test class does not support this operation");
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
