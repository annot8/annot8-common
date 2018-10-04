package io.annot8.pipelines.elements;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.helpers.WithName;
import io.annot8.core.helpers.WithProcessItem;
import io.annot8.pipelines.listeners.Listenable;
import io.annot8.pipelines.listeners.PipeListener;

public interface Pipe extends WithName, WithProcessItem, Annot8Component, Listenable<PipeListener> {



}
