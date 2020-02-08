package com.techstudio.springlite.context.support;

import com.techstudio.springlite.beans.BeansException;
import com.techstudio.springlite.beans.factory.BeanFactory;
import com.techstudio.springlite.beans.factory.NoSuchBeanDefinitionException;
import com.techstudio.springlite.beans.factory.config.AutowireCapableBeanFactory;
import com.techstudio.springlite.beans.factory.support.DefaultListableBeanFactory;
import com.techstudio.springlite.context.ApplicationContext;
import com.techstudio.springlite.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private ApplicationContext parent;

    private String id = ObjectUtils.identityToString(this);

    public AbstractApplicationContext() {
    }

    public AbstractApplicationContext(ApplicationContext parent) {
        setParent(parent);
    }

    public void refresh(){
        // 初始化beanFactory
        DefaultListableBeanFactory beanFactory= obtainFreshBeanFactory();
        // 实例化所有的单例bean
        finishBeanFactoryInitialization(beanFactory);
    }

    protected DefaultListableBeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        return getBeanFactory();
    }

    protected void finishBeanFactoryInitialization(DefaultListableBeanFactory beanFactory){

    }

    @Override
    public ApplicationContext getParent() {
        return parent;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return getBeanFactory();
    }

    //---------------------------------------------------------------------
    // Implementation of BeanFactory interface
    //---------------------------------------------------------------------
    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        return getBeanFactory().getBean(requiredType, args);
    }

    @Override
    public boolean containsBean(String beanName) {
        return getBeanFactory().containsBean(beanName);
    }

    @Override
    public Class<?> getType(String beanName) throws NoSuchBeanDefinitionException {
        return getBeanFactory().getType(beanName);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        getBeanFactory().preInstantiateSingletons();
    }

    //---------------------------------------------------------------------
    // Implementation of ListableBeanFactory interface
    //---------------------------------------------------------------------


    @Override
    public boolean containsBeanDefinition(String beanName) {
        return getBeanFactory().containsBeanDefinition(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return getBeanFactory().getBeanDefinitionCount();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return getBeanFactory().getBeanNamesForType(type);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        return getBeanFactory().getBeanNamesForAnnotation(annotationType);
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException {
        return getBeanFactory().getBeansWithAnnotation(annotationType);
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) throws NoSuchBeanDefinitionException {
        return getBeanFactory().findAnnotationOnBean(beanName, annotationType);
    }

    public void setParent(ApplicationContext parent) {
        this.parent = parent;
    }

    public abstract DefaultListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    public void setId(String id) {
        this.id = id;
    }

    protected BeanFactory getInternalParentBeanFactory() {
        return null;
    }
}
