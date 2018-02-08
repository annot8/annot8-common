package io.annot8.common.bounds;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.data.Content;
import java.util.Optional;

/**
 * Implementation of Bounds indicating that an annotation does covers the entire content.
 *
 * This class is a singleton, and should be accessed via getInstance()
 */
public final class ContentBounds implements Bounds {

  private static final ContentBounds INSTANCE = new ContentBounds();

  private ContentBounds() {
    //Empty constructor
  }


  /**
   * Return the singleton instance of ContentBounds
   */
  public static ContentBounds getInstance() {
    return INSTANCE;
  }

  @Override
  public String toString() {
    return "ContentBounds";
  }

  @Override
  public <D, C extends Content<D>, R> Optional<R> getData(C content, Class<R> requiredClass) {
    D data = content.getData();

    if (requiredClass.isAssignableFrom(data.getClass())) {
      return Optional.of((R) data);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public <D, C extends Content<D>> boolean isValid(C content) {
    return true;
  }
}
