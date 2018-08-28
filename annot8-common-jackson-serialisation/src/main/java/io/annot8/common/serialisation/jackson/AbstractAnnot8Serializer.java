package io.annot8.common.serialisation.jackson;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class AbstractAnnot8Serializer<D> extends StdSerializer<D> {

  private Class<D> clazz;

  public AbstractAnnot8Serializer(Class<D> clazz) {
    super(clazz);
    this.clazz = clazz;
  }

  /**
   * Return the class this serializer handles
   */
  public Class<D> getSerializableClass() {
    return this.clazz;
  }

}
