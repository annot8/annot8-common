package io.annot8.common.pipelines.elements;

import io.annot8.common.pipelines.listeners.JobListener;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithName;
import io.annot8.common.pipelines.listeners.Listenable;

public interface Job extends Runnable, Listenable<JobListener>, WithId, WithName {



}
