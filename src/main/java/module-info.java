/**
 * This module contains common functionality for the Annot8 framework,
 * including helper and utility functions, abstract classes, factory
 * objects, and common implementations of some components (notably
 * Bounds).
 *
 * It does not contain default implementations of the majority of components,
 * which are held in the default-impl module.
 *
 * The abstract classes in this module are there to provide correct
 * implementations of functions such as equals, hashCode and toString.
 * They do not provide any logic beyond this, and should generally be used
 * by any implementations of the interfaces they are abstracting.
 */
module io.annot8.common {
  requires io.annot8.core;
  
  
  opens io.annot8.common.annotations;
  
  // TODO Was exports not opens? But caused issues in tests
  // See https://issues.apache.org/jira/browse/MCOMPILER-341
  // Should be fixed in maven-compiler-plugin 3.7.1 (currently 3.7.0)
  opens io.annot8.common.bounds;
  opens io.annot8.common.content;
  opens io.annot8.common.factories;
  opens io.annot8.common.properties;
  opens io.annot8.common.references;
  opens io.annot8.common.registries;
  opens io.annot8.common.stores;
  opens io.annot8.common.utils;
}