package com.tunaPasta04.util;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<Object> {

    public int compare(Object obj1, Object obj2) {
        File file1 = (File) obj1;
        File file2 = (File) obj2;
        String filename1 = file1.getName();
        String filename2 = file2.getName();
        char firstChar1 = filename1.toLowerCase().charAt(0);
        char firstChar2 = filename2.toLowerCase().charAt(0);

        if (firstChar1 > firstChar2) {
            return 1;
        } else if (firstChar1 == firstChar2) {
            return 0;
        } else {
            return -1;
        }
    }
}
