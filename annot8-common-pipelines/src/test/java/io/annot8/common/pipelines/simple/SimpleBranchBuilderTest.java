package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;

import io.annot8.common.pipelines.definitions.BranchDefinition;
import io.annot8.common.pipelines.elements.Branch;
import io.annot8.core.exceptions.IncompleteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

class SimpleBranchBuilderTest {

  @Mock
  Branch branch;

  @Test
  void build() throws IncompleteException {

        BranchDefinition build = new SimpleBranchBuilder()
            .withInput("in")
            .withOutput("out")
            .use(branch)
            .build();


        assertThat(build.getInput()).isEqualTo("in");
        assertThat(build.getOutputs()).containsExactlyInAnyOrder("out");
        assertThat(build.create()).isEqualTo(branch);
    }
}