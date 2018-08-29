package io.annot8.common.serialisation.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.annot8.common.data.bounds.SpanBounds;
import java.io.IOException;

public class SpanBoundsSerializer extends AbstractAnnot8Serializer<SpanBounds> {

  public SpanBoundsSerializer() {
    super(SpanBounds.class);
  }


  @Override
  public void serialize(SpanBounds value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("begin",value.getBegin());
    gen.writeNumberField("end",value.getBegin());
    gen.writeEndObject();
  }
}
