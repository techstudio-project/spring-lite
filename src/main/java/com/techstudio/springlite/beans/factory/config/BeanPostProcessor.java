package com.techstudio.springlite.beans.factory.config;

import com.techstudio.springlite.beans.BeansException;

/**
 * @author lj
 * @date 2020/2/16
 */
public interface BeanPostProcessor {

    /**
     * bean初始化之前
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * bean初始化之后
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
