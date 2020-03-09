package com.tesla.energy.feed;

import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.HashTag;
import com.tesla.energy.model.Partition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedInterpreter {

  private static final Logger LOGGER = LoggerFactory.getLogger(FeedInterpreter.class);

  public static Optional<FeedResult> interpretLine(String feedLine) {

    LOGGER.trace("feedLine: " + feedLine);

    String[] feedParts = feedLine.split(",", 4);
    String timestamp = feedParts[0];
    if (!verifyTimestampWithEpoch(timestamp)) {
      return Optional.empty();
    }

    int partition = Integer.parseInt(feedParts[1]);
    Optional<Partition> partitionNumber = Partition.fromCode(partition);
    if (!partitionNumber.isPresent()) {
      LOGGER.debug("Invalid partitionNumber in feedLine: " + feedLine);
      return Optional.empty();
    }

    String uuid = feedParts[2];
    String hashTags = feedParts[3];
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
    LOGGER.trace("feedResult: " + feedResult.toString());
    return Optional.of(feedResult);

  }

  public static boolean verifyTimestampWithEpoch(String timestamp) {

    Calendar defaultCalendar = new GregorianCalendar();
    defaultCalendar.set(1970, 0, 1, 0, 0);
    defaultCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date defaultTime = defaultCalendar.getTime();
    Calendar timestampCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    timestampCal.setTimeInMillis(Long.valueOf(timestamp));
    Date timestampDate = timestampCal.getTime();

    if (timestampDate.before(defaultTime)) {
      LOGGER.debug(
          "Timestamp is before 1970, Jan 1st: " + timestampDate.toGMTString() + " Timestamp "
              + timestamp);
      return false;
    }
    return true;

  }
}
