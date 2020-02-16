package com.techstudio.springlite.aop;

/**
 * @author lj
 * @date 2020/2/16
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
