package com.tesla.energy.file;

import java.io.File;

public class FileUtil {

  private FileUtil() {

  }

  public static boolean verifyFilePath(String inputFilePath) {

    File tempFile = new File(inputFilePath);
    return tempFile.exists();
  }

  public static boolean verifyFileDirectory(String fileDirPath) {
    File tempFile = new File(fileDirPath);
    return tempFile.isDirectory();
  }

}
