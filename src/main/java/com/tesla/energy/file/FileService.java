package com.tesla.energy.file;

public interface FileService {

  /**
   * Takes an {@code inputPath} to read the input from, interpret/parse/process the input file and
   * write it to four different output files at output directory {@code outputDirectoryPath} based
   * on the partition value
   *
   * @param inputPath           input file path
   * @param outputDirectoryPath output directory path
   * @return a boolean to indicate the success/failure of file interpretation
   */
  boolean interpretFile(String inputPath, String outputDirectoryPath);

}
