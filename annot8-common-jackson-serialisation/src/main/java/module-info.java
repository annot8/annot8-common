module io.annot8.common.jackson.serialisation {
  requires com.fasterxml.jackson.core;
  requires io.annot8.common.data;
  requires com.fasterxml.jackson.databind;

  requires io.github.classgraph;
  requires slf4j.api;

  exports io.annot8.common.serialisation.jackson;
}