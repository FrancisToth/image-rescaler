package com.imagerescaler.writer;

import com.common.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;

import static com.common.util.FileUtil.concatFileName;

public class SystemFileWriter implements FileWriter {

    private final String destinationDirPath;

    public SystemFileWriter(String destinationDirPath) {
        this.destinationDirPath = destinationDirPath;
    }

    @Override
    public void write(InputStream is, String fileRelativePath) throws IOException {
        String absolutePath = concatFileName(destinationDirPath, fileRelativePath);
        FileUtil.mkdirFilePath(absolutePath);
        FileUtil.write(is, absolutePath);
    }
}
