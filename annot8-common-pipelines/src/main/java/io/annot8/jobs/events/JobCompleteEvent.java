package io.annot8.jobs.events;

import io.annot8.jobs.Job;

public class JobCompleteEvent extends AbstractJobEvent {

  public JobCompleteEvent(Job job) {
    super(job);
  }

}
