package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/8
 */
public class BeanDefinitionOverrideException extends BeansException {
    public BeanDefinitionOverrideException(String msg) {
        super(msg);
    }

    public BeanDefinitionOverrideException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
