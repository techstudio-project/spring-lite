package com.techstudio.springlite.beans.factory.xml;

import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;
import com.techstudio.springlite.beans.factory.config.BeanDefinitionHolder;
import com.techstudio.springlite.beans.factory.support.BeanDefinitionReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author lj
 * @date 2020/2/6
 */
public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBeanDefinitionDocumentReader.class);

    public static final String BEAN_ELEMENT = BeanDefinitionParserDelegate.BEAN_ELEMENT;

    private final BeanDefinitionReader beanDefinitionReader;

    private final BeanDefinitionParserDelegate delegate;

    public DefaultBeanDefinitionDocumentReader(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
        this.delegate = new BeanDefinitionParserDelegate(beanDefinitionReader);
    }

    @Override
    public void registerBeanDefinitions(Document doc) throws BeanDefinitionStoreException {
        doRegisterBeanDefinitions(doc.getDocumentElement());
    }

    protected void doRegisterBeanDefinitions(Element root) {
        // 与源码有出入，暂且限制<beans></beans>内部不能再定义<beans></beans>，所以这里没有实现递归
        parseBeanDefinitions(root, this.delegate);
    }

    private void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
        // 暂且不支持自定义的标签
        if (delegate.isDefaultNamespace(root)) {
            NodeList nl = root.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                if (node instanceof Element) {
                    Element ele = (Element) node;
                    if (delegate.isDefaultNamespace(ele)) {
                        parseDefaultElement(ele, delegate);
                    }
                }
            }
        }
    }

    private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
        // 先只实现bean标签
        if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
            processBeanDefinition(ele, delegate);
        }
    }

    private void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {
        BeanDefinitionHolder bdh = delegate.parseBeanDefinitionHolderElement(ele);
        try {
            beanDefinitionReader.getRegistry().registerBeanDefinition(bdh.getBeanName(), bdh.getBeanDefinition());
        } catch (BeanDefinitionStoreException e) {
            logger.error("Failed to register bean definition with name '" + bdh.getBeanName());
        }
    }

}
