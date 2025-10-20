package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements;

import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

import java.util.ArrayList;
import java.util.List;

public class QuickSortIterativeMedian3<T extends Comparable<T>> implements SortingCmp<T> {

    @Override
    public void sort(T[] data) {
        validateParams(data);
        quicksort(data);
    }

    private int medianOfThree(T[] data, int index1, int index2, int index3) {
        if (data[index1].compareTo(data[index2]) < 0) {
            if (data[index2].compareTo(data[index3]) < 0) {
                return index2;
            } else if (data[index1].compareTo(data[index3]) < 0) {
                return index3;
            } else {
                return index1;
            }
        } else {
            if (data[index1].compareTo(data[index3]) < 0) {
                return index1;
            } else if (data[index2].compareTo(data[index3]) < 0) {
                return index3;
            } else {
                return index2;
            }
        }
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
        int mid = start + (end - start) / 2;
        int medianIndex = medianOfThree(data, start, mid, end);

        swap(data, medianIndex, start);

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
