package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.MutablePropertyValues;
import com.techstudio.springlite.beans.factory.config.BeanDefinition;

/**
 * @author lj
 * @date 2020/2/5
 */
public class AbstractBeanDefinition implements BeanDefinition {

    private Class<?> beanClass;

    private String beanClassName;

    private MutablePropertyValues propertyValues;

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        if (this.propertyValues == null) {
            this.propertyValues = new MutablePropertyValues();
        }
        return this.propertyValues;
    }

    @Override
    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
