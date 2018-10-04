package io.annot8.pipelines.listeners;

import io.annot8.pipelines.events.PipeEvent;
import io.annot8.pipelines.events.SourceEvent;

@FunctionalInterface
public interface SourceListener {

  void onSourceEvent(SourceEvent event);

}
