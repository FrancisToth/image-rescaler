package com.imagerescaler.file;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;

class ExcludeWildcardFilter implements IOFileFilter {

    private final WildcardFileFilter wildcardFileFilter;

    ExcludeWildcardFilter(String...wildcards) {
        wildcardFileFilter = new WildcardFileFilter(wildcards, IOCase.INSENSITIVE);
    }

    @Override
    public boolean accept(File dir, String name) {
        return !wildcardFileFilter.accept(dir, name);
    }

    @Override
    public boolean accept(File file) {
        return !wildcardFileFilter.accept(file);
    }
}