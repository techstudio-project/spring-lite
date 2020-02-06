package com.techstudio.springlite.beans.factory.xml;

import com.techstudio.springlite.beans.PropertyValue;
import com.techstudio.springlite.beans.factory.config.*;
import com.techstudio.springlite.beans.factory.support.AbstractBeanDefinition;
import com.techstudio.springlite.beans.factory.support.BeanDefinitionReader;
import com.techstudio.springlite.beans.factory.support.GenericBeanDefinition;
import com.techstudio.springlite.util.ClassUtils;
import com.techstudio.springlite.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author lj
 * @date 2020/2/6
 */
public class BeanDefinitionParserDelegate {

    private static final Logger logger = LoggerFactory.getLogger(BeanDefinitionParserDelegate.class);

    public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

    public static final String BEAN_ELEMENT = "bean";

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";

    private final BeanDefinitionReader beanDefinitionReader;

    public BeanDefinitionParserDelegate(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
    }

    public boolean isDefaultNamespace(Node node) {
        return isDefaultNamespace(getNamespaceURI(node));
    }

    public String getNamespaceURI(Node node) {
        return node.getNamespaceURI();
    }

    public boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri)
                || BEANS_NAMESPACE_URI.equals(namespaceUri));
    }

    public boolean nodeNameEquals(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName())
                || desiredName.equals(getLocalName(node));
    }

    public String getLocalName(Node node) {
        return node.getLocalName();
    }

    public BeanDefinitionHolder parseBeanDefinitionHolderElement(Element ele) {
        String id = ele.getAttribute(ID_ATTRIBUTE);
        AbstractBeanDefinition bd = parseBeanDefinitionElement(ele);
        String beanName = this.beanDefinitionReader.getBeanNameGenerator().generateBeanName(
                bd, this.beanDefinitionReader.getRegistry());
        // id优先
        if (id != null) {
            beanName = id;
        }
        return new BeanDefinitionHolder(bd, beanName);
    }

    protected AbstractBeanDefinition parseBeanDefinitionElement(Element ele) {
        String className = null;
        if (ele.hasAttribute(CLASS_ATTRIBUTE)) {
            className = ele.getAttribute(CLASS_ATTRIBUTE).trim();
        }
        try {
            AbstractBeanDefinition bd = createBeanDefinition(className);
            // 解析构造函数参数
            parseConstructorArgElements(ele, bd);
            // 解析属性参数
            parsePropertyElements(ele, bd);
            return bd;
        } catch (ClassNotFoundException e) {
            logger.error("Bean class [" + className + "] not found", ele, e);
        }
        return null;
    }


    protected AbstractBeanDefinition createBeanDefinition(String className) throws ClassNotFoundException {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        if (className != null) {
            // 和源码不同，此处将class与className分开为两个字段
            bd.setBeanClassName(className);
            if (beanDefinitionReader.getBeanClassLoader() != null) {
                bd.setBeanClass(ClassUtils.forName(
                        className, beanDefinitionReader.getBeanClassLoader()));
            }
        }
        return bd;
    }

    protected void parsePropertyElements(Element beanEle, BeanDefinition bd) {
        NodeList nl = beanEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (nodeNameEquals(node, PROPERTY_ELEMENT)) {
                parsePropertyElement((Element) node, bd);
            }
        }
    }

    private void parsePropertyElement(Element ele, BeanDefinition bd) {
        String propertyName = ele.getAttribute(NAME_ATTRIBUTE);
        if (!StringUtils.hasLength(propertyName)) {
            logger.error("Tag 'property' must have a 'name' attribute");
            return;
        }

        if (bd.getPropertyValues().contains(propertyName)) {
            logger.error("Multiple 'property' definitions for property '" + propertyName + "'");
            return;
        }
        Object val = parsePropertyValue(ele, bd, propertyName);
        PropertyValue pv = new PropertyValue(propertyName, val);
        bd.getPropertyValues().addPropertyValue(pv);
    }

    /**
     * 为了简单起见，禁用Property的子标签
     *
     * @param ele
     * @param bd
     * @param propertyName
     * @return
     */
    protected Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element");

        boolean hasRefAttribute = ele.hasAttribute(REF_ATTRIBUTE);
        boolean hasValueAttribute = ele.hasAttribute(VALUE_ATTRIBUTE);
        if (hasRefAttribute && hasValueAttribute) {
            logger.error(elementName +
                    " is only allowed to contain either 'ref' attribute OR 'value' attribute OR sub-element", ele);
        }

        if (hasRefAttribute) {
            String refName = ele.getAttribute(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute", ele);
            }
            return new RuntimeBeanReference(refName);
        } else if (hasValueAttribute) {
            return new TypedStringValue(ele.getAttribute(VALUE_ATTRIBUTE));
        } else {
            // Neither child element nor "ref" or "value" attribute found.
            logger.error(elementName + " must specify a ref or value", ele);
            return null;
        }
    }

    protected void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
        NodeList nl = beanEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (nodeNameEquals(node, CONSTRUCTOR_ARG_ELEMENT)) {
                parseConstructorArgElement((Element) node, bd);
            }
        }
    }

    /**
     * 只实现了 name ref type value
     *
     * @param ele
     * @param bd
     */
    protected void parseConstructorArgElement(Element ele, BeanDefinition bd) {
        String typeAttr = ele.getAttribute(TYPE_ATTRIBUTE);
        String nameAttr = ele.getAttribute(NAME_ATTRIBUTE);

        Object value = parsePropertyValue(ele, bd, null);
        ConstructorArgumentValues.ValueHolder valueHolder = new ConstructorArgumentValues.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }
        bd.getConstructorArgumentValues().addGenericArgumentValue(valueHolder);
    }

}
