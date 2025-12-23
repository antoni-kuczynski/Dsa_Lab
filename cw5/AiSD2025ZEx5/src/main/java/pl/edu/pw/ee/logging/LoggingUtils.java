package pl.edu.pw.ee.logging;

public final class LoggingUtils {

    public static void printError(String error) {
        System.out.println(ColorTag.ANSI_RED + "[Error] " + error);
    }

    public static void printInfo(String info) {
        System.out.println(ColorTag.ANSI_BLUE + "[Info] " + ColorTag.ANSI_RESET + info);
    }

    public static void printSuccess(String success) {
        System.out.println(ColorTag.ANSI_GREEN + "[Success] " + success);
    }




    public static void printProgressBar(float progressValue) {
        printProgressBar(progressValue, ColorTag.EMPTY);
    }

    public static void printProgressBar(float progressValue, ColorTag colorPrefix) {
        int progressInt = (int) progressValue;
        int remaining = 100 - progressInt;

        int filled = progressInt >> 1;
        int empty = remaining >> 1;

        String bar = "\r" + colorPrefix + "[" +
                "#".repeat(filled) +
                " ".repeat(empty) +
                "]";

        System.out.print("\r");
        System.out.print(bar);
        System.out.printf(" %.2f%c", progressValue, '%');
    }

    public static int prepareProgressPrintThreshold(long total) {
        int progressThreshold = 0;
        try {
            progressThreshold = Math.toIntExact(total / 50);
        } catch (ArithmeticException e) {
            progressThreshold = 2048; //overflow
        }
        return progressThreshold;
    }
}
