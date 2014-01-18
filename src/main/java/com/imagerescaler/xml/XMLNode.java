package com.imagerescaler.xml;

import com.common.Closure;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
    private final Node wrappedNode;

    XMLNode(Node wrappedNode) {
        this.wrappedNode = wrappedNode;
    }

    public String getAttributeValue(String attributeName) {
        Node namedItem = wrappedNode.getAttributes().getNamedItem(attributeName);
        if(namedItem != null) {
            return namedItem.getNodeValue();
        }
        return "";
    }

    public boolean hasNameEqualTo(String nodeName) {
        return nodeName.equals(wrappedNode.getNodeName());
    }

    public void each(Closure<XMLNode> closure) {
        NodeList childNodes = wrappedNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            closure.execute(new XMLNode(childNodes.item(i)));
        }
    }

    public Iterable<XMLNode> childNodes() {
        return new XMLNodeIterator(wrappedNode.getChildNodes());
    }

    public Iterable<XMLNode> reverseChildNodes() {
        return new XMLNodeReverseIterator(wrappedNode.getChildNodes());
    }
}