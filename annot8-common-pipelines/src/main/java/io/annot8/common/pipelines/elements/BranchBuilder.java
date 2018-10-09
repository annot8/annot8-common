package io.annot8.common.pipelines.elements;

import io.annot8.common.pipelines.definitions.BranchDefinition;
import io.annot8.core.helpers.builders.WithBuild;
import java.util.function.Supplier;

public interface BranchBuilder extends WithBuild<BranchDefinition> {

  BranchBuilder withInput(String key);

  BranchBuilder withOutput(String key);

  default BranchBuilder use(Class<Branch> clazz) {
    return use(() -> {
      try {
        return clazz.getConstructor().newInstance();
      } catch (Exception e) {
        return null;
      }
    });
  }

  default BranchBuilder use(Branch branch) {
    return use(() -> branch);

  }

  BranchBuilder use(Supplier<Branch> branch);

}
