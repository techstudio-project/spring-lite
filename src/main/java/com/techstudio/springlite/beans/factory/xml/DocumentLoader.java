package com.techstudio.springlite.beans.factory.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author lj
 * @date 2020/2/6
 */
public interface DocumentLoader {

    Document loadDocument(InputSource inputSource) throws ParserConfigurationException,
            IOException, SAXException;

}
