package com.techstudio.springlite.util;

import java.util.Collection;

/**
 * @author lj
 * @date 2020/2/5
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

}
