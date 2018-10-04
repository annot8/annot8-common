package io.annot8.task.events;

import io.annot8.task.Task;
import io.annot8.task.TaskEvent;

public class TaskBegunEvent extends AbstractTaskEvent {

  public TaskBegunEvent(Task task) {
    super(task);
  }

}
