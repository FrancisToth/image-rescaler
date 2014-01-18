package com.imagerescaler.file;

import com.imagerescaler.writer.FileWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

public class FileSet {
    private final String originalPath;
    private final IOFileFilter filter;

    public FileSet(String originalPath) {
        this.originalPath = originalPath;
        filter = FileFileFilter.FILE;
    }

    private FileSet(String originalPath, IOFileFilter filter) {
        this.originalPath = originalPath;
        this.filter = filter;
    }

    public FileSet include(String includePattern) {
        String[] wildcards = includePattern.split(",");
        return new FileSet(originalPath, new AndFileFilter(filter, new WildcardFileFilter(wildcards, IOCase.INSENSITIVE)));
    }

    public FileSet exclude(String excludePattern) {
        String[] wildcards = excludePattern.split(",");
        return new FileSet(originalPath, new AndFileFilter(filter, new ExcludeWildcardFilter(wildcards)));
    }

    public FileSet modifiedSince(long timestamp) {
        return new FileSet(originalPath, new AndFileFilter(filter, new AgeFileFilter(timestamp, false)));
    }

    public void write(FileWriter writer) {
        Collection<File> files = FileUtils.listFiles(new File(originalPath), filter, TrueFileFilter.TRUE);
        for (File file : files) {
            try {
                String relativePath = file.getAbsolutePath().replaceAll(originalPath, "");
                writer.write(new FileInputStream(file), relativePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}