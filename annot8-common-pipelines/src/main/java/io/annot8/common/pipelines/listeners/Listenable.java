package io.annot8.common.pipelines.listeners;

public interface Listenable<L> {

  void register(L listener);

  void deregister(L listener);

}
