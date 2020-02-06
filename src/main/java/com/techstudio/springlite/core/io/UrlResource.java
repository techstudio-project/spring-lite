package com.techstudio.springlite.core.io;

import com.techstudio.springlite.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lj
 * @date 2020/2/3
 */
public class UrlResource extends AbstractResource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException e) {
            // 如果为http连接则需要断开连接，以免造成连接数满的情况
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw e;
        }
    }

    @Override
    public URL getURL() throws IOException {
        return url;
    }

    @Override
    public File getFile() throws IOException {
        return ResourceUtils.getFile(url);
    }
}
