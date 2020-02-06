package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;
import com.techstudio.springlite.core.io.Resource;
import com.techstudio.springlite.core.io.ResourceLoader;

/**
 * @author lj
 * @date 2020/2/5
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    ClassLoader getBeanClassLoader();

    BeanNameGenerator getBeanNameGenerator();

    int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(String location) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException;

}
