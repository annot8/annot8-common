package io.annot8.jobs;

import io.annot8.task.AbstractTask;

public class JobTask extends AbstractTask {

  private final Job job;

  public JobTask(Job job) {
    super(job.getId(), job.getName());
    this.job = job;
  }

  @Override
  protected void perform() {
   job.run();
  }
}
