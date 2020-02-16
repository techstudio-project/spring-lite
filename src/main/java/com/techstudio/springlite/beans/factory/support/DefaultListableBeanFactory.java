package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.BeansException;
import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;
import com.techstudio.springlite.beans.factory.BeanFactory;
import com.techstudio.springlite.beans.factory.ListableBeanFactory;
import com.techstudio.springlite.beans.factory.NoSuchBeanDefinitionException;
import com.techstudio.springlite.beans.factory.config.BeanDefinition;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lj
 * @date 2020/2/5
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements ListableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    private boolean allowBeanDefinitionOverriding = true;

    public DefaultListableBeanFactory() {
    }

    public DefaultListableBeanFactory(BeanFactory parentBeanFactory) {
        super(parentBeanFactory);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        BeanDefinition existingDefinition = beanDefinitionMap.get(beanName);
        if (existingDefinition != null) {
            if (!isAllowBeanDefinitionOverriding()) {
                throw new BeanDefinitionOverrideException("bean 重复定义：" + beanName);
            }
            // 覆盖
            beanDefinitionMap.put(beanName, beanDefinition);
        }
        else {
            beanDefinitionMap.put(beanName, beanDefinition);
            beanDefinitionNames.add(beanName);
        }
    }

    @Override
    public void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        beanDefinitionMap.remove(beanName);
        beanDefinitionNames.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        BeanDefinition bd = beanDefinitionMap.get(beanName);
        if (bd == null) {
            throw new NoSuchBeanDefinitionException("no bean definition");
        }
        return bd;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    @Override
    public boolean isBeanNameInUse(String beanName) {
        return false;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionNames.toArray(new String[0]);
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> beans = new ArrayList<>();
        for (String beanName : beanDefinitionNames) {
            Object instance = getSingleton(beanName);
            if (instance == null) {
                continue;
            }
            if (type.isInstance(instance) || type.isAssignableFrom(instance.getClass())) {
                beans.add(beanName);
            }
        }
        return beans.toArray(new String[0]);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        String[] beans = getBeanNamesForType(type);
        Map<String, T> map = new HashMap<>();
        for (String bean : beans) {
            map.put(bean, getBean(bean, type));
        }
        return map;
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        // todo
        return null;
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType)
            throws BeansException {
        //todo
        return null;
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
            throws NoSuchBeanDefinitionException {
        //todo
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBean(requiredType, (Object[]) null);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        if (args == null) {
            String[] beans = getBeanNamesForType(requiredType);
            // 有多个返回第一个
            return getBean(beans[0], requiredType);
        }
        throw new BeansException("暂不支持args参数");
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            getBean(entry.getKey());
        }
    }

    public void setAllowBeanDefinitionOverriding(boolean allowBeanDefinitionOverriding) {
        this.allowBeanDefinitionOverriding = allowBeanDefinitionOverriding;
    }

    public boolean isAllowBeanDefinitionOverriding() {
        return this.allowBeanDefinitionOverriding;
    }
}
