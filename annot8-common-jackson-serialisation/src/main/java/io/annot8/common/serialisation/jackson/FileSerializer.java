package io.annot8.common.serialisation.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.File;
import java.io.IOException;

public class FileSerializer extends AbstractAnnot8Serializer<File> {

  public FileSerializer() {
    super(File.class);
  }

  @Override
  public void serialize(File value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    gen.writeStringField("filePath", value.getAbsolutePath());
    gen.writeEndObject();
  }

}
