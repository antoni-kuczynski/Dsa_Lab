package pl.edu.pw.ee.aisd2025zex1.services;

public interface Sorting {

    void sort(int[] nums);

    default void validateParams(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input args (data) cannot be null!");
        }
    }
}
