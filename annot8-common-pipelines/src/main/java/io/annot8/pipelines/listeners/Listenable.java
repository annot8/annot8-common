package io.annot8.pipelines.listeners;

public interface Listenable<L> {

  void register(L listener);

  void deregister(L listener);

}
