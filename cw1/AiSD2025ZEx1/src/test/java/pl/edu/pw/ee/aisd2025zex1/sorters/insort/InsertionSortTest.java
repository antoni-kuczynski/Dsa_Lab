package pl.edu.pw.ee.aisd2025zex1.sorters.insort;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.StableSortTestClass;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionSortTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public InsertionSortTest() {
        super(new InsertionSort<>());
    }

    @Test
    public void shouldSortStably_WhenDataIsRepeated() {
        //given
        StableSortTestClass[] data = {
                new StableSortTestClass("a", 3),
                new StableSortTestClass("b", 3),
                new StableSortTestClass("c", 6),
                new StableSortTestClass("d", 7),
                new StableSortTestClass("e", 6),
                new StableSortTestClass("f", 9),
                new StableSortTestClass("g", 9),
                new StableSortTestClass("h", 1)
        };

        //when
        StableSortTestClass[] sortedStable = {
                new StableSortTestClass("h", 1),
                new StableSortTestClass("a", 3),
                new StableSortTestClass("b", 3),
                new StableSortTestClass("c", 6),
                new StableSortTestClass("e", 6),
                new StableSortTestClass("d", 7),
                new StableSortTestClass("f", 9),
                new StableSortTestClass("g", 9)
        };
        sorter.sort((T[]) data);


        //then
        assertArrayEquals(sortedStable, data);
    }
}
