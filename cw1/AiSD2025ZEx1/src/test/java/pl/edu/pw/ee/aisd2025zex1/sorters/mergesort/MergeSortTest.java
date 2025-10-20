package pl.edu.pw.ee.aisd2025zex1.sorters.mergesort;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public MergeSortTest() {
        super(new MergeSort<>());
    }

    @Test
    public void shouldSort_IfValuesHaveRepeatingPatterns() {
        //given
        Integer[] data = {10,20,10,20,10,20};

        //when
        Integer[] sorted = {10,10,10,20,20,20};
        sorter.sort((T[]) data);

        //then
        assertArrayEquals(sorted, data);
    }
}