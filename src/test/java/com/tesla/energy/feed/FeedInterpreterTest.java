package com.tesla.energy.feed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tesla.energy.model.FeedResult;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedInterpreterTest {

  private String feedLine;

  @BeforeEach
  public void setUp() {
    feedLine = "1505233687039,3,4fc18980-0f65-4033-b5b5-4a64effba2f5,#nine,#one,#ten,#two,#seven,#three,#eight,#six,#four";
  }

  @Test
  public void interpretLine_checkResultWhenValidLine_True() {

    Optional<FeedResult> result = FeedInterpreter.interpretLine(feedLine);
    assertEquals("1505233687039,4fc18980-0f65-4033-b5b5-4a64effba2f5,50", result.get().toString());

  }


}