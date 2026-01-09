package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements;

import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortIterativeWithInSortTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public QuickSortIterativeWithInSortTest() {
        super(new QuickSortIterativeWithInSort<>());
    }

}