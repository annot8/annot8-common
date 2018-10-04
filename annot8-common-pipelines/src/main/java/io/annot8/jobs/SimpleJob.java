package io.annot8.jobs;

import io.annot8.jobs.events.JobBegunEvent;
import io.annot8.jobs.events.JobCompleteEvent;
import io.annot8.pipelines.listeners.Listeners;
import io.annot8.task.Task;
import java.util.List;
import java.util.UUID;

public class SimpleJob implements Job {

  private final Listeners<JobListener, JobEvent> listeners = new Listeners<>(JobListener::onJobEvent);

  private final String name;
  private final List<Task> tasks;

  private final String id;

  public SimpleJob(String name, List<Task> tasks) {
    this.name = name;
    this.tasks = tasks;
    this.id = UUID.randomUUID().toString();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void register(JobListener listener) {
    listeners.register(listener);
  }

  @Override
  public void deregister(JobListener listener) {
    listeners.deregister(listener);
  }

  @Override
  public void run() {
    // TODO: This is basic - we could listen for TaskCompleteEvent
    listeners.fire(new JobBegunEvent(this));
    for(Task task : tasks) {
      task.run();
    }
    listeners.fire(new JobCompleteEvent(this));

  }

}
