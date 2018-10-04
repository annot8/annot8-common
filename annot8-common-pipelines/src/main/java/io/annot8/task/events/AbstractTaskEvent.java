package io.annot8.task.events;

import io.annot8.task.Task;
import io.annot8.task.TaskEvent;

public class AbstractTaskEvent implements TaskEvent {

  private final Task task;

  public AbstractTaskEvent(Task task) {
    this.task = task;
  }

  @Override
  public Task getTask() {
    return task;
  }
}
