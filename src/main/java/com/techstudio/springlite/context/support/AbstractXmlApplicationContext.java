package com.techstudio.springlite.context.support;

import com.techstudio.springlite.beans.BeansException;
import com.techstudio.springlite.beans.factory.support.DefaultListableBeanFactory;
import com.techstudio.springlite.beans.factory.xml.XmlBeanDefinitionReader;
import com.techstudio.springlite.context.ApplicationContext;
import com.techstudio.springlite.core.io.DefaultResourceLoader;
import com.techstudio.springlite.core.io.Resource;

import java.io.IOException;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    public AbstractXmlApplicationContext() {
    }

    public AbstractXmlApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    /**
     *
     * @param beanFactory
     * @throws BeansException
     * @throws IOException
     */
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory)
            throws BeansException {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.setResourceLoader(new DefaultResourceLoader());
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

}
