package pl.edu.pw.ee.aisd2025zex1.sorters.countingsort;

import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortPrimitiveIntTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CountingSortTest extends GeneralSortPrimitiveIntTest {

    public CountingSortTest() {
        super(new CountingSort());
    }

    @Override
    public void should_SortForRandomData() {
        //given
        int[] arr = {123,453,12,4,1,8,0,34,23};

        //when
        int[] arrSorted = new int[9];
        System.arraycopy(arr, 0, arrSorted, 0, 9);
        Arrays.sort(arrSorted);
        sorter.sort(arr);

        //then
        assertArrayEquals(arrSorted, arr);
    }
}
