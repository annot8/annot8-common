package io.annot8.common.bounds;

import java.util.Optional;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.data.Content;

/**
 * A position marker within a content.
 */
public class PositionBounds implements Bounds {

  private final int position;

  /**
   * New position at offset >= 0
   */
  public PositionBounds(int position) {
    this.position = position;
  }

  /**
   * Get the position offset
   */
  public int getPosition() {
    return position;
  }

  @Override
  public <D, C extends Content<D>, R> Optional<R> getData(C content, Class<R> requiredClass) {
    // AS this is position not a span I think it has no content
    // (arguably you could get the character at that point
    return Optional.empty();
  }

  @Override
  public <D, C extends Content<D>> boolean isValid(C content) {

    // TODO: Probably makes sense for content in core to have a getDataClass on it
    // then we don't need to get the data (which could be expensive... could eb a HTTP call
    // or something)

    // TODO: files, array, etc all have offset.
    D data = content.getData();
    if (data.getClass().equals(String.class)) {
      String s = (String) content.getData();
      return position < s.length();
    }

    return false;
  }

}
