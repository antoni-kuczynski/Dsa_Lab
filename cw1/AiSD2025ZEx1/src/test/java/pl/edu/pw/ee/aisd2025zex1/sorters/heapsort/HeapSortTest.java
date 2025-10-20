package pl.edu.pw.ee.aisd2025zex1.sorters.heapsort;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.StableSortTestClass;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HeapSortTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public HeapSortTest() {
        super(new HeapSort<>());
    }


    @Test
    public void shouldSortStably_WhenInputIsAlreadySorted() {
        //given
        StableSortTestClass[] data = {
                new StableSortTestClass("a", 1),
                new StableSortTestClass("b", 2),
                new StableSortTestClass("c", 3),
                new StableSortTestClass("d", 4),
                new StableSortTestClass("e", 5),
                new StableSortTestClass("f", 6),
                new StableSortTestClass("g", 7),
                new StableSortTestClass("h", 8)
        };

        //when
        StableSortTestClass[] sortedStable = {
                new StableSortTestClass("a", 1),
                new StableSortTestClass("b", 2),
                new StableSortTestClass("c", 3),
                new StableSortTestClass("d", 4),
                new StableSortTestClass("e", 5),
                new StableSortTestClass("f", 6),
                new StableSortTestClass("g", 7),
                new StableSortTestClass("h", 8)
        };
        sorter.sort((T[]) data);


        //then
        assertArrayEquals(sortedStable, data);
    }

    @Test
    public void shouldSort_IfValuesAreAlternatingBetweenVeryHighAndLow() {
        //given
        Integer[] data = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};

        //when
        Integer[] sorted = {Integer.MIN_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE};
        sorter.sort((T[]) data);

        //then
        assertArrayEquals(sorted, data);
    }

    @Test
    public void shouldSort_IfInputIsMaxHeapButNotSorted() {
        //given
        Integer[] data = {16,14,10,8,7,9,3,2,4,1};

        //when
        Integer[] sorted = new Integer[data.length];
        System.arraycopy(data, 0, sorted, 0, data.length);
        Arrays.sort(sorted);
        sorter.sort((T[]) data);

        //then
        assertArrayEquals(sorted, data);
    }

}
