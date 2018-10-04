package io.annot8.jobs.events;

import io.annot8.jobs.Job;
import io.annot8.jobs.JobEvent;

public abstract class AbstractJobEvent implements JobEvent {

  private final Job job;

  protected AbstractJobEvent(Job job) {
    this.job = job;
  }

  @Override
  public Job getJob() {
    return job;
  }
}
