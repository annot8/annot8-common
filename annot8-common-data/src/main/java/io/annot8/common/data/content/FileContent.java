package io.annot8.common.data.content;

import io.annot8.core.data.Content;
import java.io.File;

/**
 * Content which is backed by a file.
 *
 * In order to access this file you will need to have permissions, be on same machine, etc. As such whilst files
 * are easy to handle they aren't very useful in many cases.
 *
 * We recommend starting reading files early in the pipeline, but moving to {@link InputStreamContent}.
 *
 */
public interface FileContent extends Content<File> {

}
