package com.techstudio.springlite.beans.factory;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/6
 */
public class BeanCreationException extends BeansException {
    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
