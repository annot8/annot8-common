package io.annot8.common.bounds;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.data.Content;
import java.util.Optional;

/**
 * A position marker within a content.
 */
public class PositionBounds implements Bounds {

  private final int position;

  public PositionBounds(int position) {
    this.position = position;
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
    // then we dont' need to get the data (which could be expensive... could eb a HTTP call or somethign)

    // TODO: files, array, etc all have offset.
    D data = content.getData();
    if (data instanceof String) {
      String s = (String) content.getData();
      return isValid() && position < s.length();
    }

    return false;
  }

  public boolean isValid() {
    return position >= 0;
  }
}
