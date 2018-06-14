package org.kim.bom.util;

public class FileHelper {

    public static String getExtension(String fileName) {
        String extensionName = fileName.substring(fileName.lastIndexOf('.') + 1);
        return extensionName;
    }
}
