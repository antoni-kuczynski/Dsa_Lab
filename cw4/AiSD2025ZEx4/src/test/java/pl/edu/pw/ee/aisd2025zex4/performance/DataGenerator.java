package pl.edu.pw.ee.aisd2025zex4.performance;

import java.util.Arrays;
import java.util.Comparator;

public class DataGenerator {
    private final int stringLength;

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
                char c = (char) (33 + Math.random() * (126 - 33));
                s.append(c);
            }
            data[i] = s.toString();
        }
        return data;
    }

    public String[] generateAscData(int size) {
        String[] data = generateRandData(size);
        Arrays.sort(data, Comparator.naturalOrder());

        return data;
    }

    public String[] generateDescData(int size) {
        String[] data = generateRandData(size);
        Arrays.sort(data, Comparator.reverseOrder());

        return data;
    }

    private static void validateParam(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Array size cannot be less than 0!");
        }
    }
}
