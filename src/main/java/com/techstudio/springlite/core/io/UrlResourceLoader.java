package com.techstudio.springlite.core.io;

import com.techstudio.springlite.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lj
 * @date 2020/2/3
 */
public class UrlResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) throws FileNotFoundException {
        URL url= ResourceUtils.getUrl(location);
        return new UrlResource(url);
    }

    @Override
    public ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }
}
