package io.annot8.jobs.events;

import io.annot8.jobs.Job;
import io.annot8.task.Task;
import io.annot8.task.events.AbstractTaskEvent;

public class JobBegunEvent extends AbstractJobEvent {

  public JobBegunEvent(Job job) {
    super(job);
  }

}
