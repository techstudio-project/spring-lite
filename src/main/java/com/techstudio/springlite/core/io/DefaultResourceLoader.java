package com.techstudio.springlite.core.io;

import com.techstudio.springlite.util.ClassUtils;
import com.techstudio.springlite.util.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @author lj
 * @date 2020/2/3
 */
public class DefaultResourceLoader implements ResourceLoader {

    private static final Logger logger = LoggerFactory.getLogger(DefaultResourceLoader.class);

    private ClassLoader classLoader;

    public DefaultResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    @Override
    public Resource getResource(String location) {
        try {
            URL url = ResourceUtils.getUrl(location);
            return new UrlResource(url);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
