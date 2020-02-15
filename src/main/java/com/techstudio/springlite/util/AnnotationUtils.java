package com.techstudio.springlite.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

/**
 * @author lj
 * @date 2020/2/10
 */
public class AnnotationUtils {

    private AnnotationUtils() {
    }

    public static Annotation[] getDeclaredAnnotations(AnnotatedElement element) {
        return element.getDeclaredAnnotations();
    }

}
