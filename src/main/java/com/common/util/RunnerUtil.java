package com.common.util;

import java.util.Arrays;

public class RunnerUtil {
    public static long resolveParameter(String[] args, int index, long defaultValue) {
        try {
            return Long.parseLong(resolveMandatoryParameter(args, index));
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public static String resolveParameter(String[] args, int index, String defaultValue) {
        try {
            return resolveMandatoryParameter(args, index);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public static String resolveMandatoryParameter(String[] args, int index) {
        if (args.length > index) {
            return args[index];
        }
        throw new IllegalArgumentException("ERROR : Argument " + index + " missing [" + Arrays.toString(args) + "]");
    }
}