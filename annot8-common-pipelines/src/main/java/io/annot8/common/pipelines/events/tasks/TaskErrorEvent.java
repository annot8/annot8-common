package io.annot8.common.pipelines.events.tasks;

import io.annot8.common.pipelines.elements.Task;

public class TaskErrorEvent extends AbstractTaskEvent {

  public TaskErrorEvent(Task task) {
    super(task);
  }

}
