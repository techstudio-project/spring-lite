package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.BeansException;
import com.techstudio.springlite.beans.factory.BeanCreationException;
import com.techstudio.springlite.beans.factory.BeanFactory;
import com.techstudio.springlite.beans.factory.NoSuchBeanDefinitionException;
import com.techstudio.springlite.beans.factory.config.BeanDefinition;
import com.techstudio.springlite.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractBeanFactory.class);

    private BeanFactory parentBeanFactory;

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    public AbstractBeanFactory() {
    }

    public AbstractBeanFactory(BeanFactory parentBeanFactory) {
        this.parentBeanFactory = parentBeanFactory;
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null, null, false);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName,null,args,false);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return doGetBean(beanName,requiredType,null,false);
    }

    @Override
    public boolean containsBean(String beanName) {
        return false;
    }

    @Override
    public Class<?> getType(String beanName) throws NoSuchBeanDefinitionException {
        return null;
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String beanName, final Class<T> requiredType,
                              final Object[] args, boolean typeCheckOnly) throws BeansException {

        Object bean;
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance != null && args == null) {
            if (logger.isTraceEnabled()) {
                logger.trace("返回缓存中的单例对象：" + beanName);
            }
            bean = getObjectForBeanInstance(sharedInstance, beanName, null);
        }
        // 容器中没有，则需要重新创建实例
        else {
            // 查找父容器中是否存在bean instance
            BeanFactory parentBeanFactory = getParentBeanFactory();
            if (parentBeanFactory != null) {
                if (parentBeanFactory instanceof AbstractBeanFactory) {
                    return ((AbstractBeanFactory) parentBeanFactory).doGetBean(beanName, requiredType, args, typeCheckOnly);
                }
                else if (args != null) {
                    return (T) parentBeanFactory.getBean(beanName, args);
                }
                else if (requiredType != null) {
                    return parentBeanFactory.getBean(beanName, requiredType);
                }
                else {
                    return (T) parentBeanFactory.getBean(beanName);
                }
            }

            if (!typeCheckOnly) {
                // spring源码此处是将bean加入准备创建集合中
                markBeanAsCreated(beanName);
            }

            // 创建bean
            try {
                // 获取 BeanDefinition
                BeanDefinition bd = getBeanDefinition(beanName);

                // 创建 bean instance
                if (bd.isSingleton()) {
                    sharedInstance = getSingleton(beanName, () -> {
                        try {
                            return createBean(beanName, bd, args);
                        }
                        catch (BeansException e) {
                            destroySingleton(beanName);
                            throw e;
                        }
                    });
                    bean = getObjectForBeanInstance(sharedInstance, beanName, bd);
                }
                else {
                    throw new BeanCreationException("只支持单例bean");
                }
            }
            catch (Exception e) {
                throw new BeansException("创建bean失败", e);
            }
        }
        return (T) bean;
    }

    /**
     * spring 源码对普通bean和factoryBean在这个方法中做了不同的处理
     *
     * @param beanInstance
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected Object getObjectForBeanInstance(
            Object beanInstance, String beanName, BeanDefinition beanDefinition) {

        return beanInstance;
    }

    public BeanFactory getParentBeanFactory() {
        return parentBeanFactory;
    }

    protected void markBeanAsCreated(String beanName) {
        //todo
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition mbd, Object[] args)
            throws BeanCreationException;
}
