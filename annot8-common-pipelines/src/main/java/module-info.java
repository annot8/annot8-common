open module io.annot8.common.pipelines {
  requires com.google.common;
  requires transitive io.annot8.core;
  requires slf4j.api;
  requires io.annot8.common.implementations;

  exports io.annot8.common.pipelines;
  exports io.annot8.common.pipelines.creation.configuration;
  exports io.annot8.common.pipelines.creation;
  exports io.annot8.common.pipelines.common;
  exports io.annot8.common.pipelines.processing;
  exports io.annot8.common.pipelines.runnable;
  exports io.annot8.common.pipelines.queues;
}