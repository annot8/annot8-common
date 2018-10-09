package io.annot8.common.pipelines.simple;

import io.annot8.common.pipelines.definitions.BranchDefinition;
import io.annot8.common.pipelines.elements.Branch;
import io.annot8.common.pipelines.elements.BranchBuilder;
import io.annot8.core.exceptions.IncompleteException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class SimpleBranchBuilder implements BranchBuilder {

  private Set<String> outputs = new HashSet<>();
  private String input;
  private Supplier<Branch> supplier;

  @Override
  public BranchBuilder withInput(String key) {
    input = key;
    return this;
  }

  @Override
  public BranchBuilder withOutput(String key) {
    outputs.add(key);
    return this;
  }

  @Override
  public BranchBuilder use(Supplier<Branch> branch) {
    supplier = branch;
    return this;
  }

  @Override
  public BranchDefinition build() throws IncompleteException {
    Objects.requireNonNull(input);
    Objects.requireNonNull(supplier);

    return new BranchDefinition(input, outputs, supplier);
  }
}
