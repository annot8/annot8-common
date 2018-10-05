package io.annot8.pipelines.listeners;

import io.annot8.pipelines.events.PipelineEvent;

@FunctionalInterface
public interface PipelineListener {

  void onPipelineEvent(PipelineEvent event);

}
