package io.annot8.common.pipelines.events.tasks;

import io.annot8.common.pipelines.elements.Task;

public class TaskCompleteEvent extends AbstractTaskEvent {

  public TaskCompleteEvent(Task task) {
    super(task);
  }

}
