package com.common.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copy;

public class FileUtil {

    public static void moveDirectoryContent(String source, String destination) throws IOException {
        File sourceDirectory = new File(source);
        File destinationDirectory = new File(destination);
        if(!destinationDirectory.exists()) {
            destinationDirectory.mkdirs();
        }

        for (String fileName : sourceDirectory.list()) {
            File sourceFile = new File(sourceDirectory, fileName);
            File destinationFile = new File(destinationDirectory, fileName);
            forceMoveFile(sourceFile, destinationFile);
        }
    }

    private static void forceMoveFile(File sourceFile, File destinationFile) throws IOException {
        if(sourceFile.isFile()) {
            forceMoveSingleFile(sourceFile, destinationFile);
        } else {
            forceMoveDirectory(sourceFile, destinationFile);
        }
    }

    private static void forceMoveSingleFile(File sourceFile, File destinationFile) throws IOException {
        if(destinationFile.exists()) {
            FileUtils.deleteQuietly(destinationFile);
        }
        FileUtils.moveFile(sourceFile, destinationFile);
    }

    private static void forceMoveDirectory(File sourceFile, File destinationFile) throws IOException {
        moveDirectoryContent(sourceFile.getAbsolutePath(), destinationFile.getAbsolutePath());
        FileUtils.deleteQuietly(sourceFile);
    }

    public static String mkdirFilePath(String filePath) throws IOException {
        String path = FilenameUtils.getPath(filePath);
        if(filePath.startsWith(File.separator)) {
            path = File.separator + path;
        }
        FileUtils.forceMkdir(new File(path));
        return filePath;
    }

    public static String extractFileName(String filePath) {
        return FilenameUtils.getName(filePath);
    }

    public static String concatFileName(String dirPath, String filePath) {
        if(filePath.startsWith(File.separator)) {
            filePath = filePath.replaceFirst(File.separator, "");
        }
        return FilenameUtils.concat(dirPath, filePath);
    }

    public static void write(InputStream is, String absolutePath) throws IOException {
        FileOutputStream os = openOutputStream(new File(absolutePath));
        copy(is, os);
        closeQuietly(os);
        closeQuietly(is);
    }

    public static String getDirectoryPath(String filePath) {
        return FilenameUtils.getFullPathNoEndSeparator(filePath);
    }
}