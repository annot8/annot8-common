package io.annot8.common.data.bounds;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.data.Content;
import io.annot8.core.exceptions.InvalidBoundsException;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of Bounds for a simple 2D span, such as an offset of text.
 */
public class SpanBounds implements Bounds {

  private final int begin;
  private final int end;

  /**
   * Create a new object with the specified begin and end values
   */
  public SpanBounds(final int begin, final int end) {
    if (begin < 0) {
      throw new InvalidBoundsException("Begin must be greater than or equal to 0");
    }

    if (end < begin) {
      throw new InvalidBoundsException("End must be greater than or equal to Begin");
    }

    this.begin = begin;
    this.end = end;
  }

  /**
   * Return the begin position of this object
   */
  public int getBegin() {
    return begin;
  }

  /**
   * Return the end position of this object
   */
  public int getEnd() {
    return end;
  }

  /**
   * Return the length of this span
   */
  public int getLength() {
    return end - begin;
  }

  @Override
  public <D, C extends Content<D>, R> Optional<R> getData(C content, Class<R> requiredClass) {

    D data = content.getData();

    if (requiredClass.equals(String.class) && data.getClass().equals(String.class)) {
      String s = (String) data;

      int normBegin = Math.max(0, begin);
      int normEnd = Math.min(s.length(), end);

      @SuppressWarnings("unchecked")          // This is checked R = String.class
          R r = (R) s.substring(normBegin, normEnd);

      return Optional.of(r);
    }

    return Optional.empty();
  }

  @Override
  public <D, C extends Content<D>> boolean isValid(C content) {

    D data = content.getData();

    if (data.getClass().equals(String.class)) {
      String s = (String) data;
      return end <= s.length();
    }

    return false;
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " [begin=" + begin + ", end=" + end + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(begin, end);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SpanBounds)) {
      return false;
    }

    SpanBounds sb = (SpanBounds) o;

    return Objects.equals(begin, sb.getBegin()) && Objects.equals(end, sb.getEnd());
  }

  /**
   * Return if the provided begin and end is within this object's bounds
   */
  public boolean isWithin(int begin, int end) {
    return this.begin <= begin && end <= this.end;
  }
}
