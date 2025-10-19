package pl.edu.pw.ee.aisd2025zex1.sorters.utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Generators {

    public static Integer[] createRandomDataInteger(int size) {
        assert size >= 0;

        Integer[] nums = new Integer[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextInt();
        }

        return nums;
    }

    public static int[] createRandomDataIntPrimitive(int size) {
        assert size >= 0;

        int[] nums = new int[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextInt();
        }

        return nums;
    }

    public static Double[] createRandomDataDouble(int size) {
        assert size >= 0;

        Double[] nums = new Double[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextDouble();
        }

        return nums;
    }

    public static Double[] createAscendingData(int size) {
        assert size >= 0;

        Double[] nums = new Double[size];
        double start = 100_000_000;

        for (int i = 0; i < size; i++) {
            nums[i] = start + i;
        }

        return nums;
    }

    public static Double[] createDescendingData(int size) {
        assert size >= 0;

        Double[] nums = new Double[size];
        double start = 100_000_000;

        for (int i = size - 1; i >= 0; i--) {
            nums[i] = start + i;
        }

        return nums;
    }

    public static int[] createNonRepeatingRandomData(int size) {
        assert size >= 0;

        List<Integer> numbers = new ArrayList<>(size);
        int start = 0;
        for (int i = 0; i < size; i++) {
            numbers.add(start + i);
        }

        Collections.shuffle(numbers);

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = numbers.get(i);
        }

        return result;
    }

    public static int[] createRepeatingRandomData(int size) {
        assert size >= 0;

        int start = 0;
        int distinctValues = Math.max(1, size / 10); // pool size

        int[] pool = IntStream.range(0, distinctValues)
                .map(i -> start + i)
                .toArray();

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = pool[(int) (Math.random() * distinctValues)];
        }

        return result;
    }
}
