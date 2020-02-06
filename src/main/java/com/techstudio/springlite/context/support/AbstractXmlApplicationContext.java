package com.techstudio.springlite.context.support;

import com.techstudio.springlite.beans.BeansException;
import com.techstudio.springlite.beans.factory.support.DefaultListableBeanFactory;
import com.techstudio.springlite.beans.factory.xml.XmlBeanDefinitionReader;

import java.io.IOException;

/**
 * @author lj
 * @date 2020/2/5
 */
public abstract class AbstractXmlApplicationContext extends AbstractApplicationContext {

    /**
     *
     * @param beanFactory
     * @throws BeansException
     * @throws IOException
     */
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory)
            throws BeansException, IOException {

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

    }

}
