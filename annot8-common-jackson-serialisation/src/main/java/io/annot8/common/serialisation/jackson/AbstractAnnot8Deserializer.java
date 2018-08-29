package io.annot8.common.serialisation.jackson;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class AbstractAnnot8Deserializer<D> extends StdDeserializer<D> {

  private Class<D> clazz;

  public AbstractAnnot8Deserializer(Class<D> clazz) {
    super(clazz);
    this.clazz = clazz;
  }

  /**
   * Return the class this deserializer handles
   */
  public Class<D> getDeserializableClass() {
    return clazz;
  }

}
