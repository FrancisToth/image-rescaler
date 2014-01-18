package com.imagerescaler.writer;

import com.imagerescaler.xml.XMLNode;

public class FileWriterDecorator {

    private final FileWriter decoratedWriter;

    public FileWriterDecorator(String destinationFolderPath) {
        this.decoratedWriter = new SystemFileWriter(destinationFolderPath);
    }

    public FileWriter parse(XMLNode node) {
        return parseChildNodes(node, decoratedWriter);
    }

    private FileWriter parseChildNodes(XMLNode node, FileWriter decoratedWriter) {
        FileWriterBatch sequence = new FileWriterBatch();
        for (XMLNode childNode : node.childNodes()) {
            sequence.add(parseCommand(childNode, decoratedWriter));
        }
        return sequence;
    }

    private FileWriter parseCommand(XMLNode node, FileWriter decoratedWriter) {
        if (node.hasNameEqualTo("rename")) {
            return new RenamedImageWriter(node, decoratedWriter);
        } else if (node.hasNameEqualTo("rescale")) {
            return new RescaledImageWriter(node, decoratedWriter);
        } else if (node.hasNameEqualTo("sequence")) {
            return parseSequence(node, decoratedWriter);
        }
        return FileWriter.NULL;
    }

    private FileWriter parseSequence(XMLNode node, FileWriter decoratedWriter) {
        for (XMLNode childNode : node.reverseChildNodes()) {
            decoratedWriter = parseCommand(childNode, decoratedWriter);
        }
        return decoratedWriter;
    }
}