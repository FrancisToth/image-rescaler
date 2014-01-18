package com.imagerescaler.writer;

import com.imagerescaler.xml.XMLNode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.common.util.FileUtil.concatFileName;

class RescaledImageWriter implements FileWriter {
    private ImageScaler imageScaler = new ImageScaler();
    private final String ratio;
    private final String destinationFolder;
    private final FileWriter decoratedWriter;

    RescaledImageWriter(XMLNode node, FileWriter decoratedWriter) {
        this.decoratedWriter = decoratedWriter;
        ratio = node.getAttributeValue("ratio");
        destinationFolder = node.getAttributeValue("destination");
    }

    @Override
    public void write(InputStream fileStream, String fileRelativePath) throws IOException {
        ByteArrayOutputStream rescaledImageStream = new ByteArrayOutputStream();
        imageScaler.rescaleImage(fileStream, rescaledImageStream, ratio);

        ByteArrayInputStream rescaledImageIS = new ByteArrayInputStream(rescaledImageStream.toByteArray());
        String destination = concatFileName(destinationFolder, fileRelativePath);
        decoratedWriter.write(rescaledImageIS, destination);
    }
}