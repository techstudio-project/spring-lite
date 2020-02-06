package com.techstudio.springlite.beans.factory;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/5
 */
public class NoSuchBeanDefinitionException extends BeansException {

    public NoSuchBeanDefinitionException(String msg) {
        super(msg);
    }

    public NoSuchBeanDefinitionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
