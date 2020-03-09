package com.tesla.energy.app;

import com.tesla.energy.file.FileService;
import com.tesla.energy.file.FileServiceImpl;
import com.tesla.energy.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TeslaEnergyInterpreter {
    final static Logger LOGGER = LoggerFactory.getLogger(Class.class.getSimpleName());

    public static void main(String[] args) {

        String inputFileNamePath = args[0];
        String outputDirectoryPath = args[1];

        LOGGER.info("Input File path: ", inputFileNamePath);
        LOGGER.info("Output directory path: ", outputDirectoryPath);

        if (FileUtil.verifyFilePath(inputFileNamePath) && FileUtil
                .verifyFileDirectory(outputDirectoryPath)) {
            FileService fileProcessorService = new FileServiceImpl();
            boolean success = fileProcessorService
                    .interpretFile(inputFileNamePath, outputDirectoryPath);
            if (success) {
                LOGGER.info("Written output to directory: " + outputDirectoryPath);
            } else {
                LOGGER.error("Unable to interpret file");
            }
        }


    }
}
