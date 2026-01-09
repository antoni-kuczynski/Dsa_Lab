package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class QuickSortIterativeHoareTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public QuickSortIterativeHoareTest() {
        super(new QuickSortIterativeHoare<>());
    }


    @Test
    public void should_ThrowException_When_ThereIsNullElementInData() {
        // given
        Integer[] nums = {13,19,9,5,12,8,7,4};

        // when
        Integer[] sorted = {13,19,9,5,12,8,7,4};
        Arrays.sort(sorted);
        sorter.sort((T[]) nums);

        // then
        assertThat(nums)
                .isSorted();
    }

}