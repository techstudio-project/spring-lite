package com.techstudio.springlite.aop;

import com.techstudio.springlite.beans.BeansException;
import com.techstudio.springlite.beans.factory.BeanFactory;
import com.techstudio.springlite.beans.factory.BeanFactoryAware;
import com.techstudio.springlite.beans.factory.config.BeanPostProcessor;
import com.techstudio.springlite.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author lj
 * @date 2020/2/16
 */
public abstract class AbstractAutoProxyCreator implements
        BeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    protected static final Object[] DO_NOT_PROXY = null;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof DefaultListableBeanFactory) {
            this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        }
        else {
            throw new IllegalArgumentException("bean factory type error");
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean != null) {
            return wrapIfNecessary(bean, beanName);
        }
        return null;
    }

    protected Object wrapIfNecessary(Object bean, String beanName) {
        Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName);
        if (specificInterceptors != null && specificInterceptors.length > 0) {
            // 返回代理类实例
            return createProxy(bean, beanName, specificInterceptors);
        }
        return bean;
    }

    private Object createProxy(Object bean, String beanName, Object[] specificInterceptors) {
        return null;
    }

    protected abstract Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName);

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
