package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements;

import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

import java.util.ArrayList;
import java.util.List;

public class QuickSortIterativeRandom<T extends Comparable<T>> implements SortingCmp<T> {

    @Override
    public void sort(T[] data) {
        validateParams(data);

        quicksort(data);
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
        int pivotIndex = (int) (Math.random() * (end - start - 1)) + start;
        swap(data, pivotIndex, start);

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

}
