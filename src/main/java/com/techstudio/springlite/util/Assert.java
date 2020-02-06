package com.techstudio.springlite.util;

/**
 * @author lj
 * @date 2020/2/3
 */
public class Assert {

    private Assert() {
    }

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
