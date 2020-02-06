package com.techstudio.springlite.core.io;

import com.techstudio.springlite.util.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractResource implements Resource {

    private static Logger logger = LoggerFactory.getLogger(AbstractResource.class);

    @Override
    public boolean exists() {
        if (isFile()) {
            try {
                return getFile().exists();
            } catch (IOException e) {
                logger.debug(e.getMessage(), e);
            }
        }
        // 通过流是否能打开来判断
        try {
            getInputStream().close();
            return true;
        } catch (IOException e) {
            logger.debug(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public URI getURI() throws IOException {
        URL url = getURL();
        try {
            return ResourceUtils.toURI(url);
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI [" + url + "]", e);
        }
    }

    @Override
    public long contentLength() throws IOException {
        InputStream is = getInputStream();
        try {
            long size = 0;
            byte[] buf = new byte[256];
            int len;
            while ((len = is.read(buf)) != -1) {
                size += len;
            }
            return size;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("关闭流失败：" + e.getMessage(), e);
            }

        }
    }

    @Override
    public String getFilename() {
        return null;
    }

    @Override
    public boolean isFile() {
        return false;
    }
}
