package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;

import io.annot8.common.pipelines.definitions.MergeDefinition;
import io.annot8.common.pipelines.elements.Merge;
import io.annot8.core.exceptions.IncompleteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SimpleMergeBuilderTest {

  @Mock
  Merge merge;

  @Test
  void build() throws IncompleteException {

    MergeDefinition build = new SimpleMergeBuilder()
        .withInput("in")
        .withOutput("out")
        .use(merge)
        .build();


    assertThat(build.getInputs()).containsExactlyInAnyOrder("in");
    assertThat(build.getOutput()).isEqualTo("out");
    assertThat(build.create()).isEqualTo(merge);

  }
}