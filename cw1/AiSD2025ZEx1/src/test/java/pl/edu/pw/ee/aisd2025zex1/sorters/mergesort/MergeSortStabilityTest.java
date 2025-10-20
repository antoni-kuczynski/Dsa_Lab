package pl.edu.pw.ee.aisd2025zex1.sorters.mergesort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.StableSortTestClass;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortStabilityTest {

    private SortingCmp<StableSortTestClass> sorter;

    @BeforeEach
    public void setUp() {
        sorter = new MergeSort<>();
    }

    @Test
    public void should_SortStably_When_ValuesAreRepeated() {
        //given
        StableSortTestClass[] data = {
                new StableSortTestClass("a", 10),
                new StableSortTestClass("b", 10),
                new StableSortTestClass("c", 10),
                new StableSortTestClass("d", 10),
                new StableSortTestClass("e", 10),
                new StableSortTestClass("f", 10),
                new StableSortTestClass("g", 10),
                new StableSortTestClass("first", 1)
        };

        //when
        StableSortTestClass[] sortedStable = {
                new StableSortTestClass("first", 1),
                new StableSortTestClass("a", 10),
                new StableSortTestClass("b", 10),
                new StableSortTestClass("c", 10),
                new StableSortTestClass("d", 10),
                new StableSortTestClass("e", 10),
                new StableSortTestClass("f", 10),
                new StableSortTestClass("g", 10)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedStable, data);
    }

    @Test
    public void should_SortStably_When_ValuesAreSorted() {
        //given
        StableSortTestClass[] data = {
                new StableSortTestClass("a", 10),
                new StableSortTestClass("b", 20),
                new StableSortTestClass("c", 30),
                new StableSortTestClass("d", 40),
                new StableSortTestClass("e", 50),
                new StableSortTestClass("f", 60),
                new StableSortTestClass("g", 70)
        };

        //when
        StableSortTestClass[] sortedData = {
                new StableSortTestClass("a", 10),
                new StableSortTestClass("b", 20),
                new StableSortTestClass("c", 30),
                new StableSortTestClass("d", 40),
                new StableSortTestClass("e", 50),
                new StableSortTestClass("f", 60),
                new StableSortTestClass("g", 70)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }

    @Test
    public void should_SortStably_When_AllValuesAreEqual() {
        //given
        StableSortTestClass[] data = {
                new StableSortTestClass("a", 10),
                new StableSortTestClass("b", 10),
                new StableSortTestClass("c", 10),
                new StableSortTestClass("d", 10),
                new StableSortTestClass("e", 10),
                new StableSortTestClass("f", 10),
                new StableSortTestClass("g", 10)
        };

        //when
        StableSortTestClass[] sortedData = {
                new StableSortTestClass("a", 10),
                new StableSortTestClass("b", 10),
                new StableSortTestClass("c", 10),
                new StableSortTestClass("d", 10),
                new StableSortTestClass("e", 10),
                new StableSortTestClass("f", 10),
                new StableSortTestClass("g", 10)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }

    @Test
    public void should_SortStably_When_ValuesAreReverseSorted() {
        //given
        StableSortTestClass[] data = {
                new StableSortTestClass("a", 100),
                new StableSortTestClass("b", 90),
                new StableSortTestClass("c", 80),
                new StableSortTestClass("d", 70),
                new StableSortTestClass("e", 60),
                new StableSortTestClass("f", 50),
                new StableSortTestClass("g", 40)
        };

        //when
        StableSortTestClass[] sortedData = {
                new StableSortTestClass("g", 40),
                new StableSortTestClass("f", 50),
                new StableSortTestClass("e", 60),
                new StableSortTestClass("d", 70),
                new StableSortTestClass("c", 80),
                new StableSortTestClass("b", 90),
                new StableSortTestClass("a", 100)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }

    @Test
    public void should_SortStably_When_ValuesAreRandomAndArraySizeIsLarge() {
        //given
        int len = 100_000;
        StableSortTestClass[] data = new StableSortTestClass[len];

        for (int i = 0; i < len; i++) {
            data[i] = new StableSortTestClass(String.valueOf(i), (int) (Math.random() * 100_000));
        }
        //when
        StableSortTestClass[] sortedData = new StableSortTestClass[len];
        System.arraycopy(data, 0, sortedData, 0, len);
        Arrays.sort(sortedData);
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }
}
