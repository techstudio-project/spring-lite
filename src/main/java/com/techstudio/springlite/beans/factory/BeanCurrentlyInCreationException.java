package com.techstudio.springlite.beans.factory;


/**
 * @author lj
 * @date 2020/2/6
 */
public class BeanCurrentlyInCreationException extends BeanCreationException {

    public BeanCurrentlyInCreationException(String msg) {
        super(msg);
    }

    public BeanCurrentlyInCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
