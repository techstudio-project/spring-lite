package com.techstudio.springlite.beans.factory.config;

/**
 * @author lj
 * @date 2020/2/5
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

    int getSingletonCount();
}
