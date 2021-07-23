package com.tunaPasta04.util;

import java.io.File;
import java.io.FileFilter;

public class FileSearchFilter implements FileFilter {

    private String input_name;
    private boolean isFolder = false;

    public FileSearchFilter(String str) {
        input_name = str;
    }

    public FileSearchFilter(boolean b) {
        isFolder = b;
    }

    @Override
    public boolean accept(File file) {
        if (isFolder) {
            if (file.isDirectory()) {
                return true;
            }
            return false;
        }
        String filename = file.getName().toLowerCase();
        input_name = input_name.toLowerCase();
        if (!file.isDirectory() && filename.indexOf(input_name) >= 0) {
            return true;
        }
        return false;
    }

}
