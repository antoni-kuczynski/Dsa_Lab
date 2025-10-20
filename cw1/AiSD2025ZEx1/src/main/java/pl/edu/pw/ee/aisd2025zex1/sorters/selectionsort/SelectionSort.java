package pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort;

import pl.edu.pw.ee.aisd2025zex1.services.Sorting;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class SelectionSort<T extends Comparable<T>> implements SortingCmp<T>, Sorting {

    @Override
    public void sort(T[] data) {
        validateParams(data);

        int n = data.length;

        int minValId;

        for (int i = 0; i < n - 1; i++) {
            minValId = i;

            for (int j = i + 1; j < n; j++) {
                if (data[j].compareTo(data[minValId]) < 0) {
                    minValId = j;
                }
            }

            swap(data, i, minValId);
        }
    }

    @Override
    public void sort(int[] nums) {
        validateParams(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min])
                    min = j;
            }
            int temp = nums[i];
            nums[i] = nums[min];
            nums[min] = temp;
        }
    }

    private void swap(T[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            T firstVal = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstVal;
        }
    }
}
