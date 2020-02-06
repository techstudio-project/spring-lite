package com.techstudio.springlite.beans.factory;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/6
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
