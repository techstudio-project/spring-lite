package com.techstudio.springlite.beans;

/**
 * @author lj
 * @date 2020/2/7
 */
public class BeanInstantiationException extends BeansException {
    public BeanInstantiationException(String msg) {
        super(msg);
    }

    public BeanInstantiationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
