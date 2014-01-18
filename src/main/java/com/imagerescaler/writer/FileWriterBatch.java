package com.imagerescaler.writer;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class FileWriterBatch implements FileWriter {

    private ArrayList<FileWriter> writers = new ArrayList<FileWriter>();

    @Override
    public void write(InputStream is, String fileRelativePath) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        IOUtils.copy(is, os);
        for (FileWriter writer : writers) {
            writer.write(new ByteArrayInputStream(os.toByteArray()), fileRelativePath);
        }
    }

    public void add(FileWriter fileWriter) {
        writers.add(fileWriter);
    }
}