package com.techstudio.springlite.beans.factory;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/7
 */
public class CannotLoadBeanClassException extends BeansException {
    public CannotLoadBeanClassException(String msg) {
        super(msg);
    }

    public CannotLoadBeanClassException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
