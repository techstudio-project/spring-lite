package com.techstudio.springlite.beans.factory.xml;

import com.techstudio.springlite.beans.factory.BeanDefinitionStoreException;
import com.techstudio.springlite.beans.factory.support.AbstractBeanDefinitionReader;
import com.techstudio.springlite.beans.factory.support.BeanDefinitionRegistry;
import com.techstudio.springlite.core.io.Resource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author lj
 * @date 2020/2/5
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * xml 解析器
     */
    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                InputSource inputSource = new InputSource(inputStream);
                inputSource.setEncoding(StandardCharsets.UTF_8.toString());
                return doLoadBeanDefinitions(inputSource, resource);
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException(
                    "IOException parsing XML document from " + resource, e);
        }
    }

    protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
            throws BeanDefinitionStoreException {
        try {
            Document doc = doLoadDocument(inputSource, resource);
            return registerBeanDefinitions(doc, resource);
        } catch (BeanDefinitionStoreException ex) {
            throw ex;
        } catch (SAXParseException ex) {
            throw new XmlBeanDefinitionStoreException(
                    "Line " + ex.getLineNumber() + " in XML document from " + resource + " is invalid", ex);
        } catch (SAXException ex) {
            throw new XmlBeanDefinitionStoreException(
                    "XML document from " + resource + " is invalid", ex);
        } catch (ParserConfigurationException ex) {
            throw new BeanDefinitionStoreException(
                    "Parser configuration exception parsing XML from " + resource, ex);
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException(
                    "IOException parsing XML document from " + resource, ex);
        } catch (Throwable ex) {
            throw new BeanDefinitionStoreException(
                    "Unexpected exception parsing XML document from " + resource, ex);
        }

    }

    protected int registerBeanDefinitions(Document doc, Resource resource) {
        BeanDefinitionDocumentReader documentReader = new DefaultBeanDefinitionDocumentReader(this);
        int countBefore = getRegistry().getBeanDefinitionCount();
        documentReader.registerBeanDefinitions(doc);
        return getRegistry().getBeanDefinitionCount() - countBefore;
    }

    protected Document doLoadDocument(InputSource inputSource, Resource resource) throws Exception {
        // 简化实现
        return this.documentLoader.loadDocument(inputSource);
    }
}
