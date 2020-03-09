package com.tesla.energy.feed;

import static com.tesla.energy.model.Partition.FOUR;
import static com.tesla.energy.model.Partition.ONE;
import static com.tesla.energy.model.Partition.THREE;
import static com.tesla.energy.model.Partition.TWO;

import com.google.common.collect.Multimap;
import com.tesla.energy.file.FileServiceImpl;
import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.Partition;
import java.io.IOException;

public class FeedResultUtil {


  public static void distributedBatchWrite(FeedResult feedResult,
      Multimap<Partition, String> feedResultBatchMap, String outputFilePath, final int batchSize)
      throws IOException {

    Partition partition = feedResult.getPartition();
    String interpretedLine = feedResult.toString();

    switch (partition) {
      case ONE:
        feedResultBatchMap.put(ONE, interpretedLine);
        break;

      case TWO:
        feedResultBatchMap.put(TWO, interpretedLine);
        break;

      case THREE:
        feedResultBatchMap.put(THREE, interpretedLine);
        break;

      case FOUR:
        feedResultBatchMap.put(FOUR, interpretedLine);
        break;

      default:
        break;
    }

    if (feedResultBatchMap.size() == batchSize) {

      FileServiceImpl.writeOutputFiles(outputFilePath, feedResultBatchMap);
    }
  }
}
