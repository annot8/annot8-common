package io.annot8.task;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithName;
import io.annot8.pipelines.listeners.Listenable;

public interface Task extends Annot8Component, WithId, WithName, Runnable, Listenable<TaskListener> {

}
