package com.techstudio.springlite.util;

import com.techstudio.springlite.aop.Advisor;
import com.techstudio.springlite.aop.MethodMatcher;
import com.techstudio.springlite.aop.Pointcut;
import com.techstudio.springlite.aop.PointcutAdvisor;

/**
 * @author lj
 * @date 2020/2/16
 */
public class AopUtils {

    private AopUtils() {
    }

    public static boolean canApply(Advisor advisor, Class<?> targetClass) {
        if (advisor instanceof PointcutAdvisor) {
            return canApply(((PointcutAdvisor) advisor).getPointcut(), targetClass);
        }
        else {
            // It doesn't have a pointcut so we assume it applies.
            return true;
        }
    }

    public static boolean canApply(Pointcut pc, Class<?> targetClass) {
        if (!pc.getClassFilter().matches(targetClass)) {
            return false;
        }

        MethodMatcher methodMatcher = pc.getMethodMatcher();
        // todo
        return true;
    }
}
