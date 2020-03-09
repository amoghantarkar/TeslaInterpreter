package com.tesla.energy.file;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FileUtilTest {

  @Test
  void verifyFilePath_validPath_returnsTrue() {
    String directory = System.getProperty("user.home");
    boolean result = FileUtil.verifyFilePath(directory);
    assertTrue(result);
  }

  @Test
  void verifyFilePath_invalidPath_returnsFalse() {
    String directory = "/unknown/directory";
    boolean result = FileUtil.verifyFilePath(directory);
    assertFalse(result);
  }

  @Test
  void verifyFileDirectory_validPath_returnsTrue() {
    String directory = System.getProperty("user.home");
    boolean result = FileUtil.verifyFileDirectory(directory);
    assertTrue(result);
  }

  @Test
  void verifyFileDirectory_invalidPath_returnsFalse() {
    String directory = "/unknown/directory";
    boolean result = FileUtil.verifyFileDirectory(directory);
    assertFalse(result);
  }
}