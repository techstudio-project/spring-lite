package com.techstudio.springlite.aop;

import java.lang.reflect.Method;

/**
 * @author lj
 * @date 2020/2/16
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

    boolean isRuntime();

    boolean matches(Method method, Class<?> targetClass, Object... args);

}
