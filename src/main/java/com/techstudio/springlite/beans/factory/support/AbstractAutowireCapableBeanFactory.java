package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.BeanInstantiationException;
import com.techstudio.springlite.beans.factory.BeanCreationException;
import com.techstudio.springlite.beans.factory.config.*;
import com.techstudio.springlite.util.BeanUtils;
import com.techstudio.springlite.util.ClassUtils;
import com.techstudio.springlite.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAutowireCapableBeanFactory.class);

    @Override
    protected Object createBean(String beanName, BeanDefinition mbd, Object[] args)
            throws BeanCreationException {
        try {
            Class<?> resolvedClass = resolveBeanClass(mbd, beanName);
            mbd.setBeanClass(resolvedClass);
            Object beanInstance = doCreateBean(beanName, mbd, args);
            if (logger.isTraceEnabled()) {
                logger.trace("Finished creating instance of bean '" + beanName + "'");
            }
            return beanInstance;
        }
        catch (Exception e) {
            throw new BeanCreationException("创建bean失败", e);
        }
    }

    protected Object doCreateBean(String beanName, BeanDefinition bd, Object[] args)
            throws BeanCreationException {
        try {
            // 创建示例
            Object bean = createBeanInstance(beanName, bd, args);
            // 依赖注入
            populateBean(beanName, bd, bean);
            // 执行一些spring初始化的钩子函数
            bean = initializeBean(beanName, bean, bd);
            return bean;
        }
        catch (Exception e) {
            throw new BeanCreationException("error create bean instance", e);
        }
    }

    private void populateBean(String beanName, BeanDefinition bd, Object bean) {

    }

    protected Object createBeanInstance(String beanName, BeanDefinition bd, Object[] args) throws NoSuchMethodException {
        // 有参(使用构造器注入，对于强依赖的建议使用这种方式，这也是spring推荐的)
        if (bd.hasConstructorArgumentValues()) {
            return autowireConstructor(beanName, bd);
        }
        // 无参
        return instantiateBean(beanName, bd);
    }

    protected Object instantiateBean(String beanName, BeanDefinition bd) {
        try {
            Class<?> clazz = bd.getBeanClass();
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            return BeanUtils.instantiateClass(constructor);
        }
        catch (NoSuchMethodException e) {
            throw new BeanInstantiationException("初始化bean失败", e);
        }
    }

    protected Object autowireConstructor(String beanName, BeanDefinition bd)
            throws NoSuchMethodException {

        Constructor<?> constructor = bd.getBeanClass().getDeclaredConstructor();
        ConstructorArgumentValues argValues = bd.getConstructorArgumentValues();
        List<ConstructorArgumentValues.ValueHolder> valueHolders = argValues.getGenericArgumentValues();

        for (ConstructorArgumentValues.ValueHolder valueHolder : valueHolders) {
            // 只实现了ref
            if (valueHolder.getValue() instanceof RuntimeBeanReference) {
                RuntimeBeanReference ref = (RuntimeBeanReference) valueHolder.getValue();
                valueHolder.setValue(getBean(ref.getBeanName()));
            }
        }
        Object[] args = valueHolders.stream()
                .map(ConstructorArgumentValues.ValueHolder::getValue)
                .toArray();

        return BeanUtils.instantiateClass(constructor, args);
    }

    protected Object initializeBean(final String beanName, final Object bean, BeanDefinition bd) {
        return null;
    }

}
