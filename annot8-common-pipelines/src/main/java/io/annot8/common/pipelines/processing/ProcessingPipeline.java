/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.processing;

import io.annot8.common.pipelines.common.ItemProcessor;
import io.annot8.common.pipelines.Pipeline;
import io.annot8.core.components.Processor;
import io.annot8.core.context.Context;
import io.annot8.core.exceptions.BadConfigurationException;
import io.annot8.core.exceptions.MissingResourceException;

public interface ProcessingPipeline extends Pipeline, Processor, ItemProcessor {

  @Override
  default void configure(final Context context)
      throws BadConfigurationException, MissingResourceException {
    Pipeline.super.configure(context);
    Processor.super.configure(context);
  }
}
