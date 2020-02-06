package com.techstudio.springlite.beans.factory.xml;

import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;

/**
 * @author lj
 * @date 2020/2/5
 */
public class XmlBeanDefinitionStoreException extends BeanDefinitionStoreException {
    public XmlBeanDefinitionStoreException(String msg) {
        super(msg);
    }

    public XmlBeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
