package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.factory.BeanCreationException;
import com.techstudio.springlite.beans.factory.config.AutowireCapableBeanFactory;
import com.techstudio.springlite.beans.factory.config.BeanDefinition;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition mbd, Object[] args)
            throws BeanCreationException {

        return null;
    }
}
