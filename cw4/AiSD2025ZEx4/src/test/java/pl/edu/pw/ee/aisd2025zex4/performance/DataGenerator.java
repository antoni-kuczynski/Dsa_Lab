package pl.edu.pw.ee.aisd2025zex4.performance;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class DataGenerator {
    private final int stringLength;
    private final Random rand = new Random(31337);

    public DataGenerator() {
        stringLength = 16;
    }

    public DataGenerator(int stringLength) {
        this.stringLength = stringLength;
    }

    public String[] generateRandData(int size) {
        validateParam(size);

        String[] data = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < stringLength; j++) {
                char c = (char) rand.nextInt(33, 126-33);
                s.append(c);
            }
            data[i] = s.toString();
        }
        return data;
    }

    public String[] generateAscData(int size) {
        validateParam(size);

        String[] data = new String[size];
        for (int i = 0; i < size; i++) {
            data[i] = String.valueOf(i);
        }
        return data;
    }

    public String[] generateDescData(int size) {
        validateParam(size);

        String[] data = new String[size];
        for (int i = 0; i < size; i++) {
            data[i] = String.valueOf(size - 1 - i);
        }
        return data;
    }

    private static void validateParam(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Array size cannot be less than 0!");
        }
    }
}
