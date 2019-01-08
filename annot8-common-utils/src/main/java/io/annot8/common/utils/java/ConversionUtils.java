/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.utils.java;

import java.util.Optional;
import java.util.function.Function;

public final class ConversionUtils {

  /**
   * Converts the provided value to an Integer if possible. Note this uses the conversion methods on
   * {@Link} Number to handle conversion.
   *
   * @param valueOptional - Optional containing value to convert to Integer
   * @return Optional containing an Integer or empty if a value is not present or cannot be
   *     converted
   */
  public static Optional<Integer> toInt(Optional<Object> valueOptional) {
    return convertOptional(valueOptional, ConversionUtils::toInt);
  }

  /**
   * Converts the provided value to an Long if possible. Note this uses the conversion methods on
   * {@Link} Number to handle conversion.
   *
   * @param valueOptional Optional parameter to convert to Long
   * @return Optional containing a Long value or empty if the parameter cannot be converted
   */
  public static Optional<Long> toLong(Optional<Object> valueOptional) {
    return convertOptional(valueOptional, ConversionUtils::toLong);
  }

  private static <T> Optional<T> convertOptional(
      Optional<Object> valueOptional, Function<Object, T> function) {
    if (valueOptional.isPresent()) {
      return valueOptional.map(function);
    }
    return Optional.empty();
  }

  private static Long toLong(Object value) {
    if (value instanceof Number) {
      return ((Number) value).longValue();
    } else if (value instanceof String) {
      try {
        return Long.parseLong((String) value);
      } catch (NumberFormatException e) {
        return null;
      }
    }
    return null;
  }

  private static Integer toInt(Object value) {
    if (value instanceof Number) {
      return ((Number) value).intValue();
    } else if (value instanceof String) {
      try {
        return Integer.parseInt((String) value);
      } catch (NumberFormatException e) {
        return null;
      }
    }
    return null;
  }
}