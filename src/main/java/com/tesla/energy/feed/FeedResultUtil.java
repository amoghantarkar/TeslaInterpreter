package com.tesla.energy.feed;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.tesla.energy.file.FileServiceImpl;
import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.Partition;

import java.io.IOException;

import static com.tesla.energy.model.Partition.*;

public class FeedResultUtil {

    private static final int BATCH_SIZE = 4000;

    public static void distributedBatchWrite(FeedResult feedResult,
                                             Multimap<Partition, String> feedResultBatchMap, String outputFilePath)
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

        if (feedResultBatchMap.size() == BATCH_SIZE) {

            FileServiceImpl.writeOutputFiles(outputFilePath, feedResultBatchMap);
            feedResultBatchMap = ArrayListMultimap.create();
        }
    }
}
