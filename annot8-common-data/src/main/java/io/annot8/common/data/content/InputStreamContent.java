package io.annot8.common.data.content;

import io.annot8.core.data.Content;
import java.io.InputStream;


/**
 * Content which is backed by a input stream.
 *
 * Calling getData will re-open an inputstream each time (and the caller must close it themselves).
 *
 */
public interface InputStreamContent extends Content<InputStream> {

}
