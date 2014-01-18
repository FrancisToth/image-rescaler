package com.imagerescaler.xml;

import org.w3c.dom.NodeList;

import java.util.Iterator;

class XMLNodeReverseIterator implements Iterator<XMLNode>, Iterable<XMLNode> {

    private final NodeList nodes;
    private int index;

    XMLNodeReverseIterator(NodeList nodes) {
        this.nodes = nodes;
        index = nodes.getLength();
    }

    @Override
    public boolean hasNext() {
        return 0 < index;
    }

    @Override
    public XMLNode next() {
        return new XMLNode(nodes.item(--index));
    }

    @Override
    public void remove() {
    }

    @Override
    public Iterator<XMLNode> iterator() {
        index = nodes.getLength();
        return this;
    }
}