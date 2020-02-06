package com.techstudio.springlite.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author lj
 * @date 2020/2/3
 */
public class ResourceUtils {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    public static final String URL_PROTOCOL_FILE = "file";

    private ResourceUtils() {
    }

    /**
     * 检查resourceLocation是否是合法的地址
     *
     * @param resourceLocation 资源全路径
     * @return boolean
     */
    public static boolean isUrl(String resourceLocation) {
        if (resourceLocation == null) {
            return false;
        }
        if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            return true;
        }
        try {
            new URL(resourceLocation);
            return true;
        } catch (MalformedURLException ignore) {
            return false;
        }
    }

    /**
     * resourceLocation 转 URL ，支持标志的资源定位字符串，同时支持classpath和本地绝对路径
     *
     * @param resourceLocation 资源定位字符串
     * @return URL
     * @throws FileNotFoundException FileNotFoundException
     */
    public static URL getUrl(String resourceLocation) throws FileNotFoundException {
        Assert.notNull(resourceLocation, "Resource location must not be null");
        if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            return getClassPathUrl(resourceLocation);
        }
        try {
            return new URL(resourceLocation);
        } catch (MalformedURLException ignore) {
            try {
                return new File(resourceLocation).toURI().toURL();
            } catch (MalformedURLException e) {
                throw new FileNotFoundException("file does not exist:" + resourceLocation);
            }
        }
    }

    /**
     * 通过resourceLocation获取file对象，支持classpath、file、绝对路径
     *
     * @param resourceLocation resourceLocation
     * @return File
     * @throws FileNotFoundException FileNotFoundException
     */
    public static File getFile(String resourceLocation) throws FileNotFoundException {
        Assert.notNull(resourceLocation, "Resource location must not be null");
        if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            // classpath下的文件
            return getFile(getClassPathUrl(resourceLocation));
        }
        try {
            // 带file协议
            return getFile(new URL(resourceLocation));
        } catch (MalformedURLException e) {
            // 本地绝对路径
            return new File(resourceLocation);
        }
    }

    /**
     * 通过URL获取本地资源文件，一定是file协议才行
     *
     * @param resourceUrl resourceUrl
     * @return File
     * @throws FileNotFoundException FileNotFoundException
     */
    public static File getFile(URL resourceUrl) throws FileNotFoundException {
        Assert.notNull(resourceUrl, "Resource URL must not be null");
        if (!URL_PROTOCOL_FILE.equals(resourceUrl.getProtocol())) {
            throw new FileNotFoundException("Resource protocol does not found in file system: " + resourceUrl);
        }
        try {
            return new File(toURI(resourceUrl).getSchemeSpecificPart());
        } catch (Exception ignore) {
            return new File(resourceUrl.getFile());
        }
    }

    public static URI toURI(URL url) throws URISyntaxException {
        return toURI(url.toString());
    }

    public static URI toURI(String location) throws URISyntaxException {
        return new URI(StringUtils.replace(location, " ", "%20"));
    }

    /**
     * 获取classpath下的资源文件URL
     *
     * @param resourceLocation resourceLocation
     * @return URL
     * @throws FileNotFoundException FileNotFoundException
     */
    public static URL getClassPathUrl(String resourceLocation) throws FileNotFoundException {
        Assert.notNull(resourceLocation, "Resource location must not be null");
        if (!resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            throw new IllegalArgumentException("resourceLocation must be start with " + CLASSPATH_URL_PREFIX);
        }
        String path = resourceLocation.substring(CLASSPATH_URL_PREFIX.length());
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        URL url;
        if (cl != null) {
            url = cl.getResource(path);
        } else {
            url = ClassLoader.getSystemResource(path);
        }
        if (url == null) {
            throw new FileNotFoundException("file does not exist:" + resourceLocation);
        }
        return url;
    }

}
