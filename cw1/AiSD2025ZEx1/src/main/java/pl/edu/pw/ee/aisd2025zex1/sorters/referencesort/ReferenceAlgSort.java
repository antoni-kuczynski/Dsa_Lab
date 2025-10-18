package pl.edu.pw.ee.aisd2025zex1.sorters.referencesort;

import java.util.Arrays;
import static java.util.Objects.isNull;

import pl.edu.pw.ee.aisd2025zex1.services.Sorting;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class ReferenceAlgSort<T extends Comparable<T>> implements SortingCmp<T>, Sorting {

    @Override
    public void sort(T[] data) {
        validateInput(data);

        Arrays.sort(data);
    }

    @Override
    public void sort(int[] nums) {
        Arrays.sort(nums);
    }

    private void validateInput(T[] data) {
        if (isNull(data)) {
            throw new RuntimeException("Input args (data) cannot be null!");
        }
    }
}
