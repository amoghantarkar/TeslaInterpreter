package com.tesla.energy.model;

import java.util.Objects;

public class FeedResult {

  private String timestamp;
  private String uuid;
  private int hashTagSum;
  private Partition partition;


  public FeedResult(String timestamp, String uuid, int hashTagSum,
      Partition partition) {
    this.timestamp = timestamp;
    this.uuid = uuid;
    this.hashTagSum = hashTagSum;
    this.partition = partition;
  }

  @Override
  public String toString() {
    return timestamp + ',' + uuid + ',' + hashTagSum;
  }

  public Partition getPartition() {
    return partition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeedResult that = (FeedResult) o;
    return hashTagSum == that.hashTagSum
        &&
        Objects.equals(timestamp, that.timestamp)
        &&
        Objects.equals(uuid, that.uuid)
        && partition == that.partition;
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, uuid, hashTagSum, partition);
  }

}
