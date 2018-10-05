package io.annot8.common.pipelines.simple;

import io.annot8.common.pipelines.elements.Job;
import io.annot8.common.pipelines.elements.Task;
import io.annot8.common.pipelines.elements.JobBuilder;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SimpleJobBuilder implements JobBuilder {

  private String name = "anonymous";
  private final List<Task> tasks = new LinkedList<>();

  @Override
  public JobBuilder withName(String name) {
    Objects.requireNonNull(name);
    return this;
  }

  @Override
  public JobBuilder withTask(Task task) {
    Objects.requireNonNull(task);
    tasks.add(task);
    return this;
  }

  @Override
  public Job build() {
    return new SimpleJob(name, tasks);
  }
}
