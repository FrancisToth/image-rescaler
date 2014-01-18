package com.imagerescaler.xml;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;

import static com.common.util.StreamUtil.getResourceAsStream;


public class XMLFileParser {

    public XMLFile parseXMLFile(String xmlFilePath, String xsdFilePath) throws SAXException, ParserConfigurationException, IOException {
        InputStream xmlFile = getResourceAsStream(xmlFilePath);
        DocumentBuilder dBuilder = newDocumentBuilder(xsdFilePath);
        dBuilder.setErrorHandler(new DefaultHandler());
        return new XMLFile(dBuilder.parse(xmlFile));
    }

    private DocumentBuilder newDocumentBuilder(String xsdFilePath) throws SAXException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new StreamSource(getResourceAsStream(xsdFilePath)));
        documentBuilderFactory.setSchema(schema);
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        documentBuilderFactory.setNamespaceAware(true);
        return documentBuilderFactory.newDocumentBuilder();
    }
}