package pl.edu.pw.ee.aisd2025zex1.sorters.countingsort;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortPrimitiveIntTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CountingSortTest extends GeneralSortPrimitiveIntTest {

    public CountingSortTest() {
        super(new CountingSort());
    }

    private CountingSort getCountingSortFromSorter() {
        if (sorter instanceof CountingSort) {
            return (CountingSort) sorter;
        }

        throw new IllegalStateException("Sorter in counting sort test should be counting sort. (Should never happen)");
    }

    @Override
    public void should_SortForRandomData() {
        //given
        int[] arr = {123,453,12,4,1,8,0,34,23};

        //when
        int[] arrSorted = new int[arr.length];
        System.arraycopy(arr, 0, arrSorted, 0, arr.length);
        Arrays.sort(arrSorted);
        sorter.sort(arr);

        //then
        assertArrayEquals(arrSorted, arr);
    }

    @Test
    public void should_ThrowExpcetionWhen_MaxNumberIsSmallerThanInput() {
        //given
        int[] arr = {123456789, 1,2,3,4,5};
        getCountingSortFromSorter().setMaxVal(10);

        // when
        Throwable e = catchThrowable(() -> {
            sorter.sort(arr);
        });

        // then
        String message = "No number can be greater than \"maxVal\"";

        assertThat(e)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }

    @Test
    public void should_ThrowExpcetionWhen_ThereIsANegativeNumberInData() {
        //given
        int[] arr = {1,5,6,3,-1};

        // when
        Throwable e = catchThrowable(() -> {
            sorter.sort(arr);
        });

        // then
        String message = "No number can be less than zero";

        assertThat(e)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }

    @Test
    public void should_SortWhenMaxVal_IsMuchGreaterThanInput() {
        //given
        int[] arr = {1,5,4,3,6,2};
        getCountingSortFromSorter().setMaxVal(2_000_000_000);

        //when
        int[] arrSorted = new int[arr.length];
        System.arraycopy(arr, 0, arrSorted, 0, arr.length);
        Arrays.sort(arrSorted);
        sorter.sort(arr);

        //then
        assertArrayEquals(arrSorted, arr);
    }
}
