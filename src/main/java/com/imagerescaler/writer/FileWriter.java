package com.imagerescaler.writer;

import java.io.IOException;
import java.io.InputStream;

public interface FileWriter {
    FileWriter NULL = new FileWriter(){
        @Override
        public void write(InputStream is, String fileRelativePath) throws IOException {
        }
    };
    void write(InputStream is, String fileRelativePath) throws IOException;
}