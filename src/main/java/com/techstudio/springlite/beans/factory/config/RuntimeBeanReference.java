package com.techstudio.springlite.beans.factory.config;

/**
 * @author lj
 * @date 2020/2/6
 */
public class RuntimeBeanReference implements BeanReference {

    private final String beanName;

    private final Class<?> beanType;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
        this.beanType = null;
    }

    public RuntimeBeanReference(String beanName, Class<?> beanType) {
        this.beanName = beanName;
        this.beanType = beanType;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    public Class<?> getBeanType() {
        return beanType;
    }
}
