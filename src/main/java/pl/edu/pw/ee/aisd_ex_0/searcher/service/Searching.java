package pl.edu.pw.ee.aisd_ex_0.searcher.service;

public interface Searching {

    int search(int[] nums, int toFind);

    default boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }
}
