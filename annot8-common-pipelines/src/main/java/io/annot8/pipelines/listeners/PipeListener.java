package io.annot8.pipelines.listeners;

import io.annot8.pipelines.events.PipeEvent;

@FunctionalInterface
public interface PipeListener {

  void onPipeEvent(PipeEvent event);

}
