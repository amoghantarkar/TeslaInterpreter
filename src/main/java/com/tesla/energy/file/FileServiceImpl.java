package com.tesla.energy.file;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.tesla.energy.feed.FeedInterpreter;
import com.tesla.energy.feed.FeedResultUtil;
import com.tesla.energy.model.FeedResult;
import com.tesla.energy.model.Partition;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Optional;
import org.slf4j.LoggerFactory;

public class FileServiceImpl implements FileService {

  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(FileServiceImpl.class);
  private static final int PARTITION_MAX = 4;
  private static final String OUTPUT_FILE_PREFIX_NAME = "output-file-";

  /**
   * Writes to all the output files based on the partition
   *
   * @param outputFilePath output file path
   * @param feedResultBatchMap a {@link Multimap} of batch of interpreted lines
   */
  public static boolean writeOutputFiles(final String outputFilePath,
      Multimap<Partition, String> feedResultBatchMap) throws IOException {

    boolean operationSuccess = false;
    for (int i = 1; i <= PARTITION_MAX; i++) {
      String outputPartitionFilePath =
          outputFilePath + i + ".csv"; //produces as many files as the PARTITION_MAX
      File outputFile = new File(outputPartitionFilePath);

      if (!outputFile.exists()) {
        if (!outputFile.createNewFile()) {
          LOGGER.error("Unable to create File:" + outputFile.getPath());
        }
      }

      try (PrintWriter writer = new PrintWriter(outputPartitionFilePath, "UTF-8")) {
        Collection<String> partitionLines = feedResultBatchMap
            .get(Partition.fromCode(i).get());
        for (String result : partitionLines) {
          writer.println(result);
        }
      }
      operationSuccess = true;
    }

    return operationSuccess;

  }

  @Override
  public boolean interpretFile(String inputFilePath, String outputFileDirPath) {

    boolean operationSuccess = false;
    final int batchSize = 4000;
    String outputFilePath = outputFileDirPath + OUTPUT_FILE_PREFIX_NAME;
    Multimap<Partition, String> feedResultBatchMap = ArrayListMultimap.create();
    String line;

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
      while ((line = reader.readLine()) != null) {
        Optional<FeedResult> feedResult = FeedInterpreter.interpretLine(line);
        if (feedResult.isPresent()) {
          FeedResultUtil
              .distributedBatchWrite(feedResult.get(), feedResultBatchMap, outputFilePath,
                  batchSize);
        }
      }

      if (!feedResultBatchMap.isEmpty()) {
        LOGGER.debug("Writing result to output files: " + outputFilePath);
        writeOutputFiles(outputFilePath, feedResultBatchMap);
      }

      operationSuccess = true;
    } catch (IOException e) {
      LOGGER.error("Unable to process the input file " + inputFilePath, e);
    }

    return operationSuccess;
  }


}