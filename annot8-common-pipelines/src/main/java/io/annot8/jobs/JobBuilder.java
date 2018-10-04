package io.annot8.jobs;

import io.annot8.core.helpers.builders.WithSave;
import io.annot8.task.Task;

public interface JobBuilder  {

  JobBuilder withName(String name);

  JobBuilder withTask(Task task);

  Job build();
}
