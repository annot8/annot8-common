package io.annot8.common.pipelines.events.jobs;

import io.annot8.common.pipelines.elements.Job;

public class JobBegunEvent extends AbstractJobEvent {

  public JobBegunEvent(Job job) {
    super(job);
  }

}
