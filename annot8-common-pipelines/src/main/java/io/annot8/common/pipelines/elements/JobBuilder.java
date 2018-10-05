package io.annot8.common.pipelines.elements;

public interface JobBuilder  {

  JobBuilder withName(String name);

  JobBuilder withTask(Task task);

  Job build();
}
