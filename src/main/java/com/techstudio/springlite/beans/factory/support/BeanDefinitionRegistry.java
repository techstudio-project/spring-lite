package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;
import com.techstudio.springlite.beans.factory.NoSuchBeanDefinitionException;
import com.techstudio.springlite.beans.factory.config.BeanDefinition;

/**
 * @author lj
 * @date 2020/2/5
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionStoreException;

    void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

    int getBeanDefinitionCount();

    boolean isBeanNameInUse(String beanName);

}
