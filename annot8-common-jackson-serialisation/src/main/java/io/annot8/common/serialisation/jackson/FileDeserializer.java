package io.annot8.common.serialisation.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;

public class FileDeserializer extends AbstractAnnot8Deserializer<File> {

  public FileDeserializer() {
    super(File.class);
  }

  @Override
  public File deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    String filePath = node.get("filePath").asText();
    return new File(filePath);
  }

}
