package com.techstudio.springlite.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author lj
 * @date 2020/2/10
 */
public class AnnotatedElementUtils {

    private AnnotatedElementUtils() {
    }

    public static boolean isAnnotated(AnnotatedElement element, String annotationName) {
        Annotation[] annotations = element.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.getName().equals(annotationName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnnotated(AnnotatedElement element, Class<? extends Annotation> annotationClass) {
        Annotation[] annotations = element.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.equals(annotationClass)) {
                return true;
            }
        }
        return false;
    }
}
