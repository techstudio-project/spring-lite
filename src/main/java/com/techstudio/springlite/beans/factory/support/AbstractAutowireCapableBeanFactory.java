package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.BeanInstantiationException;
import com.techstudio.springlite.beans.MutablePropertyValues;
import com.techstudio.springlite.beans.PropertyValue;
import com.techstudio.springlite.beans.factory.BeanCreationException;
import com.techstudio.springlite.beans.factory.BeanFactory;
import com.techstudio.springlite.beans.factory.config.*;
import com.techstudio.springlite.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAutowireCapableBeanFactory.class);

    public AbstractAutowireCapableBeanFactory() {
    }

    public AbstractAutowireCapableBeanFactory(BeanFactory parentBeanFactory) {
        super(parentBeanFactory);
    }

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
            // 创建实例
            Object bean = createBeanInstance(beanName, bd, args);
            // 依赖注入
            populateBean(beanName, bd, bean);
            // 执行一些spring初始化的钩子函数比如InitializingBean的afterPropertiesSet()
            // BeanPostProcessor的postProcessBeforeInitialization 、postProcessAfterInitialization
            bean = initializeBean(beanName, bean, bd);
            return bean;
        }
        catch (Exception e) {
            throw new BeanCreationException("error create bean instance", e);
        }
    }

    private void populateBean(String beanName, BeanDefinition bd, Object bean)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        MutablePropertyValues mpvs = bd.getPropertyValues();
        List<PropertyValue> pvs = mpvs.getPropertyValueList();
        for (PropertyValue pv : pvs) {
            if (pv.getValue() instanceof RuntimeBeanReference) {
                RuntimeBeanReference reference = (RuntimeBeanReference) pv.getValue();
                String methodName = "set" + reference.getBeanName().substring(0, 1).toUpperCase()
                        + reference.getBeanName().substring(1);
                Object refVal = getBean(reference.getBeanName());
                Method method = bean.getClass().getDeclaredMethod(methodName, refVal.getClass());
                method.setAccessible(true);
                method.invoke(bean, refVal);
            }
        }
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

        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }

        Constructor<?> constructor = bd.getBeanClass().getDeclaredConstructor(parameterTypes);

        return BeanUtils.instantiateClass(constructor, args);
    }

    protected Object initializeBean(final String beanName, final Object bean, BeanDefinition bd) {
        return bean;
    }

}
