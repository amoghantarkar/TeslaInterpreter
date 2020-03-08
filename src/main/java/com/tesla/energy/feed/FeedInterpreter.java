package com.tesla.energy.feed;

import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.HashTag;
import com.tesla.energy.model.Partition;
import java.util.Optional;

public class FeedInterpreter {

  private FeedInterpreter() {

  }

  public static Optional<FeedResult> interpretLine(String feedLine) {

    String[] feedParts = feedLine.split(",", 4);
    String timestamp = feedParts[0];
    String uuid = feedParts[2];
    String hashTags = feedParts[3];

    int partition = Integer.parseInt(feedParts[1]);
    Optional<Partition> partitionNumber = Partition.fromCode(partition);
    if (!partitionNumber.isPresent()) {
      return Optional.empty();
    }

    String[] hashtagCodes = hashTags.split(",");

    int hashtagSum = 0;

    for (String hashtagCode : hashtagCodes) {

      Optional<HashTag> hashTagVal = HashTag.get(hashtagCode);
      if (hashTagVal.isPresent()) {
        hashtagSum += hashTagVal.get().getCode();
      } else {
        return Optional.empty();
      }
    }

    FeedResult feedResult = new FeedResult(timestamp, uuid, hashtagSum, partitionNumber.get());
    return Optional.of(feedResult);

  }
}
