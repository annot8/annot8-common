package io.annot8.common.registries;

import io.annot8.common.factories.ContentBuilderFactory;
import io.annot8.core.data.Content;
import java.util.Optional;

public interface ContentBuilderFactoryRegistry {

  <D, C extends Content<D>> Optional<ContentBuilderFactory<D, C>> get(Class<C> contentClass);
}
