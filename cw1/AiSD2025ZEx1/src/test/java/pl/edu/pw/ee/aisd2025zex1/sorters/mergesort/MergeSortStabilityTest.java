package pl.edu.pw.ee.aisd2025zex1.sorters.mergesort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortStabilityTest<T> {

    private SortingCmp<TestClass> sorter;

    private static class TestClass implements Comparable<TestClass> {
        private final String s;
        private final int a;

        TestClass(String s, int a) {
            this.s = s;
            this.a = a;
        }

        @Override
        public int compareTo(TestClass o) {
            return Integer.compare(this.a, o.a);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            TestClass testClass = (TestClass) o;
            return a == testClass.a && Objects.equals(s, testClass.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(s, a);
        }

        @Override
        public String toString() {
            return "TestClass{" +
                    "s='" + s + '\'' +
                    ", a=" + a +
                    '}';
        }
    }


    @BeforeEach
    public void setUp() {
        sorter = new MergeSort<>();
    }

    @Test
    public void should_SortStably_When_ValuesAreRepeated() {
        //given
        TestClass[] data = {
                new TestClass("a", 10),
                new TestClass("b", 10),
                new TestClass("c", 10),
                new TestClass("d", 10),
                new TestClass("e", 10),
                new TestClass("f", 10),
                new TestClass("g", 10),
                new TestClass("first", 1)
        };

        //when
        TestClass[] sortedStable = {
                new TestClass("first", 1),
                new TestClass("a", 10),
                new TestClass("b", 10),
                new TestClass("c", 10),
                new TestClass("d", 10),
                new TestClass("e", 10),
                new TestClass("f", 10),
                new TestClass("g", 10)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedStable, data);
    }

    @Test
    public void should_SortStably_When_ValuesAreSorted() {
        //given
        TestClass[] data = {
                new TestClass("a", 10),
                new TestClass("b", 20),
                new TestClass("c", 30),
                new TestClass("d", 40),
                new TestClass("e", 50),
                new TestClass("f", 60),
                new TestClass("g", 70)
        };

        //when
        TestClass[] sortedData = {
                new TestClass("a", 10),
                new TestClass("b", 20),
                new TestClass("c", 30),
                new TestClass("d", 40),
                new TestClass("e", 50),
                new TestClass("f", 60),
                new TestClass("g", 70)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }

    @Test
    public void should_SortStably_When_AllValuesAreEqual() {
        //given
        TestClass[] data = {
                new TestClass("a", 10),
                new TestClass("b", 10),
                new TestClass("c", 10),
                new TestClass("d", 10),
                new TestClass("e", 10),
                new TestClass("f", 10),
                new TestClass("g", 10)
        };

        //when
        TestClass[] sortedData = {
                new TestClass("a", 10),
                new TestClass("b", 10),
                new TestClass("c", 10),
                new TestClass("d", 10),
                new TestClass("e", 10),
                new TestClass("f", 10),
                new TestClass("g", 10)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }

    @Test
    public void should_SortStably_When_ValuesAreReverseSorted() {
        //given
        TestClass[] data = {
                new TestClass("a", 100),
                new TestClass("b", 90),
                new TestClass("c", 80),
                new TestClass("d", 70),
                new TestClass("e", 60),
                new TestClass("f", 50),
                new TestClass("g", 40)
        };

        //when
        TestClass[] sortedData = {
                new TestClass("g", 40),
                new TestClass("f", 50),
                new TestClass("e", 60),
                new TestClass("d", 70),
                new TestClass("c", 80),
                new TestClass("b", 90),
                new TestClass("a", 100)
        };
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }

    @Test
    public void should_SortStably_When_ValuesAreRandomAndArraySizeIsLarge() {
        //given
        int len = 100_000;
        TestClass[] data = new TestClass[len];

        for (int i = 0; i < len; i++) {
            data[i] = new TestClass(String.valueOf(i), (int) (Math.random() * 100_000));
        }
        //when
        TestClass[] sortedData = new TestClass[len];
        System.arraycopy(data, 0, sortedData, 0, len);
        Arrays.sort(sortedData);
        sorter.sort(data);


        //then
        assertArrayEquals(sortedData, data);
    }
}
