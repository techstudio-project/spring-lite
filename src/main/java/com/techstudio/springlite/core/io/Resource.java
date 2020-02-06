package com.techstudio.springlite.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * @author lj
 * @date 2020/2/3
 */
public interface Resource extends InputStreamSource {

    boolean exists() throws IOException;

    URL getURL() throws IOException;

    URI getURI() throws IOException;

    boolean isFile();

    File getFile() throws IOException;

    long contentLength() throws IOException;

    String getFilename();
}
