package com.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StreamUtil {
    public static InputStream getResourceAsStream(String resourceFilePath) {
        try {
            return new FileInputStream(resourceFilePath);
        } catch (FileNotFoundException e) {
            return StreamUtil.class.getClassLoader().getResourceAsStream(resourceFilePath);
        }
    }
}