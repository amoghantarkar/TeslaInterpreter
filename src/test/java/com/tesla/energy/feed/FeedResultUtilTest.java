package com.tesla.energy.feed;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.Partition;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;


public class FeedResultUtilTest {

  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(Class.class.getSimpleName());
  FeedResult feedResult;
  Multimap<Partition, String> feedResultBatchMap;
  String outputFilePath;

  @BeforeEach
  public void setup() {
    String feedLine = "1505233687039,3,4fc18980-0f65-4033-b5b5-4a64effba2f5,#nine,#one,#ten,#two,#seven,#three,#eight,#six,#four";
    feedResult = new FeedResult("1505233687039", "4fc18980-0f65-4033-b5b5-4a64effba2f5",
        50, Partition.THREE);

    feedResultBatchMap = ArrayListMultimap.create();
    feedResultBatchMap.put(Partition.THREE, feedLine);
    feedResultBatchMap.put(Partition.TWO, feedLine);
    feedResultBatchMap.put(Partition.ONE, feedLine);
    File file = new File(
        getClass().getClassLoader().getResource("sample.txt").getFile()
    );
    outputFilePath = file.getPath();


  }

  @Test
  void distributedBatchWrite_IfWritingToMultipleFiles_returnsTrue() {
    try {
      int batchSize = 1000;
      FeedResultUtil
          .distributedBatchWrite(feedResult, feedResultBatchMap, outputFilePath, batchSize);
    } catch (IOException e) {
      LOGGER.warn("Test failed: " + e);
    }
  }

}