package com.techstudio.springlite.util;

/**
 * @author lj
 * @date 2020/2/3
 */
public class ClassUtils {

    private ClassUtils() {
    }

    /**
     * 获取默认的ClassLoader
     * <p>
     * 获取顺序如下
     * 1.线程上下文获取
     * 2.加载此类的ClassLoader
     * 3.获取系统默认的ClassLoader（一般是加载应用的ClassLoader）
     *
     * @return ClassLoader
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            // 先从线程中获取ClassLoader
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignore) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // 线程中获取失败，则获取加载此类的ClassLoader
            cl = ClassUtils.class.getClassLoader();

            if (cl == null) {
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ignore) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }

}
