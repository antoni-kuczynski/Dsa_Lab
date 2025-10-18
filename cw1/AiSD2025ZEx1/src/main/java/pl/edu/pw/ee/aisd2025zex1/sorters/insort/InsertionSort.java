package pl.edu.pw.ee.aisd2025zex1.sorters.insort;

import static java.util.Objects.isNull;

import pl.edu.pw.ee.aisd2025zex1.services.Sorting;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class InsertionSort<T extends Comparable<T>> implements SortingCmp<T>, Sorting {

    @Override
    public void sort(T[] data) {
        validateParams(data);

        int n = data.length;
        T currentVal;
        int j;

        for (int i = 1; i < n; i++) {

            currentVal = data[i];

            for (j = i - 1; j >= 0 && data[j].compareTo(currentVal) > 0; j--) {
                data[j + 1] = data[j];
            }
            j++;

            data[j] = currentVal;
        }
    }

    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = temp;
        }
    }

    private void validateParams(T[] data) {
        if (isNull(data)) {
            throw new RuntimeException("Input args (data) cannot be null!");
        }
    }
}
