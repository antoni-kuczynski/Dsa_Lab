package pl.edu.pw.ee.aisd2025zex1.sorters.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

import java.util.Arrays;

public class GeneralSortPrimitiveIntTest {

    protected Sorting sorter;

    public GeneralSortPrimitiveIntTest(Sorting sorter) {
        this.sorter = sorter;
    }

    @Test
    public void should_ThrowException_When_InputIsNull() {
        //given
        int[] arr = null;

        //when
        Throwable ex = catchThrowable(() -> {
            sorter.sort(arr);
        });

        //then
        assertThat(ex).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_ReturnEmptyArrIfEmpty() {
        //given
        int[] arr = {};

        //when
        sorter.sort(arr);

        //then
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void should_SortIfAlreadySorted() {
        //given
        int[] arr = {1,2,3,4};

        //when
        sorter.sort(arr);

        //then
        assertArrayEquals(new int[]{1,2,3,4}, arr);
    }

    @Test
    public void should_SortIfReverseSorted() {
        //given
        int[] arr = {4,3,2,1};

        //when
        sorter.sort(arr);

        //then
        assertArrayEquals(new int[]{1,2,3,4}, arr);
    }

    @Test
    public void should_SortIfOnlyFirstElementNotSorted() {
        //given
        int[] arr = {4,1,2,3};

        //when
        sorter.sort(arr);

        //then
        assertArrayEquals(new int[]{1,2,3,4}, arr);
    }

    @Test
    public void should_SortIfArrayHasOneElement() {
        //given
        int[] arr = {123};

        //when
        sorter.sort(arr);

        //then
        assertArrayEquals(new int[]{123}, arr);
    }

    @Test
    public void should_SortForOddSizeArrays() {
        //given
        int[] arr = {4,1,2};

        //when
        sorter.sort(arr);

        //then
        assertArrayEquals(new int[]{1,2,4}, arr);
    }

    @Test
    public void should_SortForEvenSizeArrays() {
        //given
        int[] arr = {4,1,2,3};

        //when
        sorter.sort(arr);

        //then
        assertArrayEquals(new int[]{1,2,3,4}, arr);
    }

    @Test
    public void should_SortForRandomData() {
        //given
        int[] arr = {123,453,12,4,1,8,-1,34,23};

        //when
        int[] arrSorted = new int[9];
        System.arraycopy(arr, 0, arrSorted, 0, 9);
        Arrays.sort(arrSorted);
        sorter.sort(arr);

        //then
        assertArrayEquals(arrSorted, arr);
    }
}
