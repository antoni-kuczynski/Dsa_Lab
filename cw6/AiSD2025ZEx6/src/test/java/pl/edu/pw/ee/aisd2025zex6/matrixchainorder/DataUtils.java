package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import java.util.Random;

public class DataUtils {

    public int[] prepareArrayWithTheSameValue(int size, int value) {
        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = value;
        }

        return data;
    }

    public int[] prepareRandomValidArray(int numberOfMatrices, int minDim, int maxDim) {
        Random rand = new Random();
        int[] dims = new int[numberOfMatrices + 1];

        // generate first dimension
        dims[0] = rand.nextInt(maxDim - minDim + 1) + minDim;

        // generate subsequent dimensions
        for (int i = 1; i <= numberOfMatrices; i++) {
            dims[i] = rand.nextInt(maxDim - minDim + 1) + minDim;
        }

        return dims;
    }
}
