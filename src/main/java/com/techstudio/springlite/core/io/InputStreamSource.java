package com.techstudio.springlite.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lj
 * @date 2020/2/3
 */
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;

}
