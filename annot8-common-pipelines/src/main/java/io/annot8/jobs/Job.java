package io.annot8.jobs;

import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.WithName;
import io.annot8.pipelines.listeners.Listenable;

public interface Job extends Runnable, Listenable<JobListener>, WithId, WithName {



}
