package io.annot8.task;

import io.annot8.pipelines.listeners.Listeners;
import io.annot8.task.events.TaskBegunEvent;
import io.annot8.task.events.TaskCompleteEvent;
import io.annot8.task.events.TaskErrorEvent;
import java.util.UUID;

public abstract class AbstractTask implements Task {

  private final Listeners<TaskListener, TaskEvent> listeners = new Listeners<>(TaskListener::onTaskEvent);

  private final String name;
  private final String id;

  public AbstractTask(String name) {
    this(UUID.randomUUID().toString(), name);
  }

  public AbstractTask(String id, String name) {
    this.name = name;
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void run() {
    try {
      listeners.fire(new TaskBegunEvent(this));

      perform();

      listeners.fire(new TaskCompleteEvent(this));
    } catch (Exception e) {
      // TODO: Log via abstractcomponent functionality (but it'll create a cicular dependency atm)
      e.printStackTrace();
      listeners.fire(new TaskErrorEvent(this));
    }
  }

  protected abstract void perform();

  @Override
  public void register(TaskListener listener) {
    listeners.register(listener);
  }

  @Override
  public void deregister(TaskListener listener) {
    listeners.deregister(listener);
  }
}
