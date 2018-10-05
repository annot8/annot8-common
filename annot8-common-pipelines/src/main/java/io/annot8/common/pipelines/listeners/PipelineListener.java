package io.annot8.common.pipelines.listeners;

import io.annot8.common.pipelines.events.PipelineEvent;

@FunctionalInterface
public interface PipelineListener {

  void onPipelineEvent(PipelineEvent event);

}
