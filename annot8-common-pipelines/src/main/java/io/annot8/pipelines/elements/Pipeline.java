package io.annot8.pipelines.elements;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithName;
import io.annot8.pipelines.listeners.Listenable;
import io.annot8.pipelines.listeners.PipeListener;

public interface Pipeline extends WithId, WithName, Annot8Component, Listenable<PipeListener> {

}
