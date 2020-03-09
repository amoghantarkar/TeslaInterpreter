package com.tesla.energy.file;

import java.io.File;

public class FileUtil {
    /**
     * Verify that the file path exists on the file system before writing to a file
     *
     * @param inputFilePath input file path
     * @return a boolean
     */
    public static boolean verifyFilePath(String inputFilePath) {
        File tempFile = new File(inputFilePath);
        return tempFile.exists();
    }

    /**
     * Verify if the output directory exists before writing files into the directory
     *
     * @param fileDirPath output directory path
     * @return a boolean
     */
    public static boolean verifyFileDirectory(String fileDirPath) {
        File tempFile = new File(fileDirPath);
        return tempFile.isDirectory();
    }

}
