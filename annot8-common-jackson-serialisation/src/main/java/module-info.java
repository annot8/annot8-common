module io.annot8.common.jackson.serialisation {
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires io.github.classgraph;
  requires slf4j.api;
  requires io.annot8.common.data;

  exports io.annot8.common.serialisation.jackson;
}
