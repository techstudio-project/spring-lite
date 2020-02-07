package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.MutablePropertyValues;
import com.techstudio.springlite.beans.factory.config.BeanDefinition;
import com.techstudio.springlite.beans.factory.config.ConstructorArgumentValues;

/**
 * @author lj
 * @date 2020/2/5
 */
public class AbstractBeanDefinition implements BeanDefinition {

    private Class<?> beanClass;

    private String beanClassName;

    private MutablePropertyValues propertyValues;

    private ConstructorArgumentValues constructorArgumentValues;

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public ConstructorArgumentValues getConstructorArgumentValues() {
        if (this.constructorArgumentValues == null) {
            this.constructorArgumentValues = new ConstructorArgumentValues();
        }
        return constructorArgumentValues;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return getConstructorArgumentValues().isEmpty();
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

    public void setConstructorArgumentValues(ConstructorArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }
}
