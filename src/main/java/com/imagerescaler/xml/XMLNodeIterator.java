package com.imagerescaler.xml;

import org.w3c.dom.NodeList;

import java.util.Iterator;

class XMLNodeIterator implements Iterator<XMLNode>, Iterable<XMLNode> {

    private final NodeList nodes;
    private int index = 0;

    public XMLNodeIterator(NodeList nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean hasNext() {
        return nodes.getLength() > index;
    }

    @Override
    public XMLNode next() {
        return new XMLNode(nodes.item(index++));
    }

    @Override
    public void remove() {
    }

    @Override
    public Iterator<XMLNode> iterator() {
        index = 0;
        return this;
    }
}
