package com.techstudio.springlite.beans.factory.config;

import com.techstudio.springlite.beans.MutablePropertyValues;

/**
 * @author lj
 * @date 2020/2/5
 */
public interface BeanDefinition {

    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    void setBeanClass(Class<?> beanClass);

    Class<?> getBeanClass();

    ConstructorArgumentValues getConstructorArgumentValues();

    default boolean hasConstructorArgumentValues() {
        return !getConstructorArgumentValues().isEmpty();
    }

    MutablePropertyValues getPropertyValues();

    default boolean isSingleton() {
        return true;
    }

}
