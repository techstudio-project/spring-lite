package com.techstudio.springlite.beans;

/**
 * @author lj
 * @date 2020/2/5
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
