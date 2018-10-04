package io.annot8.task.events;

import io.annot8.task.Task;

public class TaskCompleteEvent extends AbstractTaskEvent {

  public TaskCompleteEvent(Task task) {
    super(task);
  }

}
