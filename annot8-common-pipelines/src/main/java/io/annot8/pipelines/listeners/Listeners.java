package io.annot8.pipelines.listeners;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BiConsumer;

public class Listeners<L, E> {

  private final BiConsumer<L, E> publish;
  private Set<L> listeners = null;

  public Listeners(BiConsumer<L, E> publish) {
    this.publish = publish;
  }

  public void register(L listener) {
    if(publish == null) {
      return;
    }

    Objects.requireNonNull(listener);

    if(listeners == null) {
      listeners = new CopyOnWriteArraySet<>();
    }

    listeners.add(listener);
  }

  public void deregister(L listener) {
    if(listeners != null) {
      listeners.remove(listener);
    }
  }

  public void fire(E event) {
    if(listeners != null && event != null && publish != null) {
      listeners.forEach(l -> publish.accept(l, event));
    }
  }

  public void clear() {
    if(listeners != null) {
      listeners.clear();
    }
  }
}