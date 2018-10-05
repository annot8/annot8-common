package io.annot8.common.pipelines.listeners;

import io.annot8.common.pipelines.events.JobEvent;

public interface JobListener {

  void onJobEvent(JobEvent event);
}
