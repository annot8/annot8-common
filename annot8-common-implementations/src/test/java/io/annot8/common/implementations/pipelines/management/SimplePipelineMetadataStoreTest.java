// TODO: This is a cirular dependency (common-impl -> impl-tcl -> test-impl -> common-impl). Test should not depend on common-impl?

//package io.annot8.common.implementations.pipelines.management;
//
//import org.junit.jupiter.api.BeforeEach;
//
//public class SimplePipelineMetadataStoreTest
//    extends AbstractPipelineMetadataStoreTest {
//
//  private PipelineMetadataStore pipelineStoreService;
//
//  @BeforeEach
//  public void before() {
//    pipelineStoreService =
//        new SimplePipelineMetadataStore(SimplePipelineMetadata.Builder::new));
//  }
//
//  @Override
//  protected PipelineMetadataStore getPipelineMetadataStoreService() {
//    return pipelineStoreService;
//  }
//
//}
