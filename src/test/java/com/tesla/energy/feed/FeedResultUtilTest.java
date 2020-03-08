package com.tesla.energy.feed;

import com.google.common.collect.Multimap;
import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.Partition;
import org.junit.jupiter.api.BeforeEach;


public class FeedResultUtilTest {

  FeedResult feedResult;
  Multimap<Partition, String> feedResultBatchMap;
  String outputFilePath;

  @BeforeEach
  public void setup() {
    String feedLine = "1505233687039,3,4fc18980-0f65-4033-b5b5-4a64effba2f5,#nine,#one,#ten,#two,#seven,#three,#eight,#six,#four";
    FeedResult feedResult = new FeedResult("1505233687039", "4fc18980-0f65-4033-b5b5-4a64effba2f5",
        50, Partition.THREE);
  }
}