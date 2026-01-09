package pl.edu.pw.ee.aisd2025zex1.services;

import static java.util.Objects.isNull;

public interface SortingCmp<T extends Comparable<T>> {

    void sort(T[] nums);

    default void validateParams(T[] data) {
        if (isNull(data)) {
            throw new IllegalArgumentException("Input args (data) cannot be null!");
        }

        for (int i = 0; i < data.length; i++) {
            if (isNull(data[i])) {
                throw new IllegalArgumentException("Input args (data) cannot contain null elements!");
            }
        }
    }
    
}
