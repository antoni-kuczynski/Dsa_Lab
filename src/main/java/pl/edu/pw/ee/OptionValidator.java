package pl.edu.pw.ee;

import pl.edu.pw.ee.logging.LoggingUtils;
import pl.edu.pw.ee.services.HuffmanService;

import java.io.File;
import java.io.IOException;

public final class OptionValidator {

    static void validateInputFileFromArgs(File file) {
        if (!file.exists()) {
            LoggingUtils.printError("Specified file does not exist!");
            System.exit(0);
        }

        if (!file.canRead() || !file.canWrite()) {
            LoggingUtils.printError("Cannot read/write from specified input file!");
            System.exit(0);
        }
    }

    static void validateOutputFileFromArgsAndCreateItIfNeeded(File file) throws IOException {
        if (!file.exists() && !file.createNewFile()) {
            LoggingUtils.printError("Failed to create the output file!");
            System.exit(0);
        }

        if (!file.canRead() || !file.canWrite()) {
            LoggingUtils.printError("Cannot read/write from specified output file!");
            System.exit(0);
        }
    }

    static void validateChainLength(int chainLength) {
        if (chainLength <= 0) {
            LoggingUtils.printError("Chain length value should be greater than 1!");
            System.exit(0);
        }

        if (chainLength > 1 << 16) {
            LoggingUtils.printInfo("Large chain length value detected! JVM heap size may need to be increased for the program to work");
        }
    }

    static void validateRequiredLaunchOptions(HuffmanService service, File inputPath, File outputPath) {
        if (service == null) {
            LoggingUtils.printError("Missing -m option!");
            System.exit(0);
        }

        if (inputPath == null) {
            LoggingUtils.printError("Missing -s option!");
            System.exit(0);
        }

        if (outputPath == null) {
            LoggingUtils.printError("Missing -d option!");
            System.exit(0);
        }
    }
}
