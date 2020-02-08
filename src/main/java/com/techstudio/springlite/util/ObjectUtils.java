package com.techstudio.springlite.util;

/**
 * @author lj
 * @date 2020/2/8
 */
public class ObjectUtils {

    private static final String EMPTY_STRING = "";

    private ObjectUtils(){}

    public static String identityToString( Object obj) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        String className = obj.getClass().getName();
        String identityHexString = getIdentityHexString(obj);
        return className + '@' + identityHexString;
    }

    public static String getIdentityHexString(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }
}
