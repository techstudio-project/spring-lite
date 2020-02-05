package com.techstudio.springlite.core.io;

import java.io.FileNotFoundException;

/**
 * @author lj
 * @date 2020/2/3
 */
public interface ResourceLoader {

    /**
     * 从指定位置加载资源文件
     *
     * @param location 资源路径
     * @return Resource
     */
    Resource getResource(String location) throws FileNotFoundException;

    /**
     * 获取加载ResourceLoader的类加载器
     *
     * @return ClassLoader
     */
    ClassLoader getClassLoader();
}
