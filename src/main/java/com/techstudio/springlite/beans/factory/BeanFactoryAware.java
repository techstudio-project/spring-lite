package com.techstudio.springlite.beans.factory;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/16
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
