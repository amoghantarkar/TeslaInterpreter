package com.tesla.energy.file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.Partition;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileServiceImplTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(Class.class.getSimpleName());
  String inputFilePath;
  String outputFileDirPath;
  FeedResult feedResult;
  Multimap<Partition, String> feedResultBatchMap;
  String outputFilePath;

  @BeforeEach
  public void setup() {
    File file = new File(
        getClass().getClassLoader().getResource("sample.txt").getFile()
    );
    inputFilePath = file.getPath();

    File outputFile = new File(
        getClass().getClassLoader().getResource("output.csv").getFile()
    );
    String feedLine = "1505233687039,3,4fc18980-0f65-4033-b5b5-4a64effba2f5,#nine,#one,#ten,#two,#seven,#three,#eight,#six,#four";
    feedResult = new FeedResult("1505233687039", "4fc18980-0f65-4033-b5b5-4a64effba2f5",
        50, Partition.THREE);

    feedResultBatchMap = ArrayListMultimap.create();
    feedResultBatchMap.put(Partition.THREE, feedLine);
    feedResultBatchMap.put(Partition.TWO, feedLine);
    feedResultBatchMap.put(Partition.ONE, feedLine);
    feedResultBatchMap.put(Partition.FOUR, feedLine);
    outputFileDirPath = outputFile.getPath().split("output.csv")[0];
    LOGGER.info("Input File path: " + inputFilePath);
    LOGGER.info("Output directory path: " + outputFileDirPath);

  }

  @Test
  public void writeOutputFiles() throws IOException {

    boolean isSuccess = FileServiceImpl.writeOutputFiles(outputFileDirPath, feedResultBatchMap);
    assertTrue(isSuccess);
  }

  @Test
  public void interpretFile() {
    FileService fileService = new FileServiceImpl();
    assertTrue(fileService.interpretFile(inputFilePath, outputFileDirPath));

  }
}