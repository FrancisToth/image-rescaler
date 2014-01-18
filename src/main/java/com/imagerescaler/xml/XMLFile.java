package com.imagerescaler.xml;

import com.common.Closure;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLFile {
    private final Document document;

    XMLFile(Document document) {
        this.document = document;
    }

    public void each(String tagname, Closure<XMLNode> closure) {
        NodeList filesets = document.getElementsByTagName(tagname);
        XMLNodeIterator it = new XMLNodeIterator(filesets);
        for (XMLNode xmlNode : it) {
            closure.execute(xmlNode);
        }
    }
}