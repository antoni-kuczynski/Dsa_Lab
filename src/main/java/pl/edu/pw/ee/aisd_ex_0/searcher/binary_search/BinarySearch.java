package pl.edu.pw.ee.aisd_ex_0.searcher.binary_search;

import pl.edu.pw.ee.aisd_ex_0.searcher.service.Searching;

import java.util.Objects;

public class BinarySearch implements Searching {

    @Override
    public int search(int[] nums, int toFind) {
        validateData(nums);

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] < toFind) {
                left = middle + 1;
            } else if (nums[middle] > toFind) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private void validateData(int[] nums) {
        if (Objects.isNull(nums)) {
            throw new IllegalArgumentException("The nums array is null.");
        }

        if (!isSorted(nums)) {
            throw new IllegalArgumentException("The nums array is not sorted in ascending order.");
        }
    }
}
