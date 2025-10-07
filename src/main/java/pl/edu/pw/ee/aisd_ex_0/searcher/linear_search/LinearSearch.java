package pl.edu.pw.ee.aisd_ex_0.searcher.linear_search;

import pl.edu.pw.ee.aisd_ex_0.searcher.service.Searching;

import java.util.Objects;

public class LinearSearch implements Searching {

    @Override
    public int search(int[] nums, int toFind) {
        validateData(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == toFind) {
                return i;
            }
        }
        return -1;
    }
    
    private void validateData(int[] nums) {
        if (Objects.isNull(nums)) {
            throw new IllegalArgumentException("The nums array is null.");
        }
    }

}
