package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements;

import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
import pl.edu.pw.ee.aisd2025zex1.sorters.insort.InsertionSort;

import java.util.ArrayList;
import java.util.List;

public class QuickSortIterativeWithInSort<T extends Comparable<T>> implements SortingCmp<T> {
    private int subProblemSize = 20;


    @Override
    public void sort(T[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input args (data) cannot be null!");
        }

        quicksort(data);
    }

    private void insort(T[] data, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T temp = data[i];
            int j = i - 1;

            while (j >= left && data[j].compareTo(temp) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = temp;
        }

//        int n = data.length;
//        T currentVal;
//        int j;
//
//        for (int i = left + 1; i <= right; i++) {
//
//            currentVal = data[i];
//
//            for (j = i - 1; j >= left && data[j].compareTo(currentVal) > 0; j--) {
//                data[j + 1] = data[j];
//            }
//            j++;
//
//            data[j] = currentVal;
//        }
    }

    private void quicksort(T[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        if (left < right) {
            while (n > 0) {
                n--;
                left = starts.remove(n);
                right = ends.remove(n);

                if (right - left < subProblemSize) {
//                    InsertionSort
                    insort(data, left, right);
                    continue;
                }

                pivot = partition(data, left, right);

                if (pivot > left) {
                    starts.add(left);
                    ends.add(pivot);
                    n++;
                }

                if (pivot + 1 < right) {
                    starts.add(pivot + 1);
                    ends.add(right);
                    n++;
                }
            }
        }
    }

    private int partition(T[] data, int start, int end) {
        T pivot = data[start];

        int left = start - 1;
        int right = end + 1;

        while (true) {

            while (data[++left].compareTo(pivot) < 0) {
            }

            while (data[--right].compareTo(pivot) > 0) {
            }

            if (left < right) {
                swap(data, left, right);
            } else {
                break;
            }

        }

        return right;
    }

    private void swap(T[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            T firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }

    public void setSubProblemSize(int val) {
        this.subProblemSize = val;
    }
}
