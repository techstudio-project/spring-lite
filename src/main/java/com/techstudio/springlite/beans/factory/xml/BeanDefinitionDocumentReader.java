package com.techstudio.springlite.beans.factory.xml;

import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;
import org.w3c.dom.Document;

/**
 * @author lj
 * @date 2020/2/6
 */
public interface BeanDefinitionDocumentReader {

    void registerBeanDefinitions(Document doc) throws BeanDefinitionStoreException;

}
