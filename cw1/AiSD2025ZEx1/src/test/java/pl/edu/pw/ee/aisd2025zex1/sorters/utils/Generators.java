package pl.edu.pw.ee.aisd2025zex1.sorters.utils;

import java.util.Random;

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
}
