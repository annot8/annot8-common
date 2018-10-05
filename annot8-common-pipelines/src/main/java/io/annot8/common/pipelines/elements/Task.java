package io.annot8.common.pipelines.elements;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithName;
import io.annot8.common.pipelines.listeners.Listenable;
import io.annot8.common.pipelines.listeners.TaskListener;

public interface Task extends Annot8Component, WithId, WithName, Runnable, Listenable<TaskListener> {

}
