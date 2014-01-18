package com.imagerescaler.writer;

import com.common.util.FileUtil;
import com.imagerescaler.xml.XMLNode;

import java.io.IOException;
import java.io.InputStream;

import static com.common.util.FileUtil.concatFileName;
import static com.common.util.FileUtil.extractFileName;

class RenamedImageWriter implements FileWriter {

    private final String pattern;
    private final String replacement;
    private final FileWriter decoratedWriter;

    RenamedImageWriter(XMLNode node, FileWriter decoratedWriter) {
        this.decoratedWriter = decoratedWriter;
        pattern = node.getAttributeValue("pattern");
        replacement = node.getAttributeValue("replacement");
    }

    @Override
    public void write(InputStream is, String fileRelativePath) throws IOException {
        String newName = extractFileName(fileRelativePath).replaceAll(pattern, replacement);
        String directoryPath = FileUtil.getDirectoryPath(fileRelativePath);
        decoratedWriter.write(is, concatFileName(directoryPath, newName));
    }
}