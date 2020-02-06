package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;
import com.techstudio.springlite.core.io.DefaultResourceLoader;
import com.techstudio.springlite.core.io.Resource;
import com.techstudio.springlite.core.io.ResourceLoader;
import com.techstudio.springlite.util.Assert;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    private ClassLoader beanClassLoader;

    private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    @Override
    public BeanNameGenerator getBeanNameGenerator() {
        return this.beanNameGenerator;
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException {
        Assert.notNull(resources, "Resource array must not be null");
        int count = 0;
        for (Resource resource : resources) {
            count += loadBeanDefinitions(resource);
        }
        return count;
    }

    @Override
    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
        Assert.notNull(location, "location must not be null");
        ResourceLoader resourceLoader = getResourceLoader();
        if (resourceLoader == null) {
            throw new BeanDefinitionStoreException(
                    "Cannot load bean definitions from location [" + location + "]: no ResourceLoader available");
        }
        Resource resource;
        try {
            resource = resourceLoader.getResource(location);
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("Cannot load bean definitions from location :" + e.getMessage(), e);
        }
        return loadBeanDefinitions(resource);
    }

    @Override
    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
        Assert.notNull(locations, "Location array must not be null");
        int count = 0;
        for (String location : locations) {
            count += loadBeanDefinitions(location);
        }
        return count;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
        this.beanNameGenerator = beanNameGenerator;
    }

}
