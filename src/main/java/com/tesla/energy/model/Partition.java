package com.tesla.energy.model;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public enum Partition {

  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4);

  private static final Map<Integer, Partition> lookup = Arrays.stream(Partition.values())
      .collect(toMap(Partition::getCode, Function.identity()));
  public final int code;

  Partition(int code) {
    this.code = code;
  }

  public static Optional<Partition> fromCode(int code) {
    return Optional.ofNullable(lookup.get(code));
  }

  public int getCode() {
    return code;
  }


}

