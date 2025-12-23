package pl.edu.pw.ee;

import pl.edu.pw.ee.logging.LoggingUtils;
import pl.edu.pw.ee.services.HuffmanService;
import pl.edu.pw.ee.services.HuffmanCompressor;
import pl.edu.pw.ee.services.HuffmanDecompressor;

import java.io.File;
import java.io.IOException;

import static pl.edu.pw.ee.OptionValidator.*;

public class Main {

    public static void main(String[] args) {
        long time = System.nanoTime();
        HuffmanService service = null;
        File inputPath = null;
        File outputPath = null;
        int maxChainLength = 1;

        for (int i = 1; i < args.length; i++) {
            String optarg = args[i];
            switch (args[i-1]) {
                case "-m" -> {
                    if (optarg.equals("comp")) {
                        service = new HuffmanCompressor();
                    } else if (optarg.equals("decomp")) {
                        service = new HuffmanDecompressor();
                    } else {
                        LoggingUtils.printError("Invalid operation mode value!");
                        System.exit(0);
                    }
                }
                case "-s" -> {
                    File file = new File(optarg);
                    validateInputFileFromArgs(file);
                    inputPath = file;
                }
                case "-d" -> {
                    File file = new File(optarg);
                    try {
                        validateOutputFileFromArgsAndCreateItIfNeeded(file);
                    } catch (IOException e) {
                        LoggingUtils.printError("I/O error occurred during output file validation!");
                    }

                    outputPath = file;
                }
                case "-l" -> {
                    int value = 1;
                    try {
                        value = Integer.parseInt(optarg);
                    } catch (NumberFormatException e) {
                        LoggingUtils.printError("Invalid chain length value!");
                        System.exit(0);
                    }
                    validateChainLength(maxChainLength);
                    maxChainLength = value;
                }
            }
        }

        validateRequiredLaunchOptions(service, inputPath, outputPath);

        int bufSize = 1 << 20;
        try {
            service.apply(inputPath, outputPath, maxChainLength, bufSize);
        } catch (IOException e) {
            LoggingUtils.printError("File operation failed!");
            System.exit(0);
        }
        long currentTime = System.nanoTime();
        LoggingUtils.printInfo("Operation took: " + (currentTime - time) + " ns");
    }
}