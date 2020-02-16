package com.techstudio.springlite.aop;

import org.aopalliance.aop.Advice;

/**
 * @author lj
 * @date 2020/2/16
 */
public interface Advisor {

    Advice EMPTY_ADVICE = new Advice() {};

    Advice getAdvice();

}
