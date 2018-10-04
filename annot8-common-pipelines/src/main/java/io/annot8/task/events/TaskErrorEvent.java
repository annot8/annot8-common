package io.annot8.task.events;

import io.annot8.task.Task;

public class TaskErrorEvent extends AbstractTaskEvent {

  public TaskErrorEvent(Task task) {
    super(task);
  }

}
