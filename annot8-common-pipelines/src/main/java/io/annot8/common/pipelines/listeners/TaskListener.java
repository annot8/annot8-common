package io.annot8.common.pipelines.listeners;

import io.annot8.common.pipelines.events.TaskEvent;

public interface TaskListener {

  void onTaskEvent(TaskEvent event);
}
