package io.annot8.common.references;

import io.annot8.core.annotations.Group;
import java.util.Optional;

public interface GroupReference {

  Optional<Group> toGroup();

}
