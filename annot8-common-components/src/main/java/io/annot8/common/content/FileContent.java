package io.annot8.common.content;

import io.annot8.core.data.Content;
import java.io.File;

// TODO: I'm not sure this is the abstraction we want.
// File is nice and easy, but isn't helpful for thing like
// 'path to file in zip'. This could be Content<URL> or perhaps something more
// abstract like Content<InputStream> with properties for mime, etc.
// Since we just want this to drop into something like TikaProcessor (FileContent -> Text)
// we don't want too many types to deal with. Though perhaps having
// special cases for File, ArchiveFile vs RemoteURL is a good idea?
// we could have a FileContent extends InputStreamProvider, Content<File>
public interface FileContent extends Content<File> {

}
