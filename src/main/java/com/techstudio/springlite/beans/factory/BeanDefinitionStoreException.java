package com.techstudio.springlite.beans.factory;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/5
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg) {
        super(msg);
    }

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
