package com.techstudio.springlite.beans.factory.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author lj
 * @date 2020/2/6
 */
public class DefaultDocumentLoader implements DocumentLoader {

    @Override
    public Document loadDocument(InputSource inputSource) throws ParserConfigurationException,
            IOException, SAXException {

        DocumentBuilderFactory factory = createDocumentBuilderFactory();
        DocumentBuilder builder = createDocumentBuilder(factory);
        return builder.parse(inputSource);
    }

    protected DocumentBuilder createDocumentBuilder(DocumentBuilderFactory factory)
            throws ParserConfigurationException {

        return factory.newDocumentBuilder();
    }

    protected DocumentBuilderFactory createDocumentBuilderFactory() {
        return DocumentBuilderFactory.newInstance();
    }
}
