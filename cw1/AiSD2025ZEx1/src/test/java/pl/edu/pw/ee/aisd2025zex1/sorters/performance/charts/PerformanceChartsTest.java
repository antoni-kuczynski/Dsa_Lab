package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.String.format;
import static java.util.logging.Level.SEVERE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
import pl.edu.pw.ee.aisd2025zex1.sorters.heapsort.HeapSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.insort.InsertionSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.mergesort.MergeSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.QuickSortIterativeHoare;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.QuickSortIterativeLomuto;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements.QuickSortIterativeMedian3;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements.QuickSortIterativeRandom;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements.QuickSortIterativeWithInSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.recursive.QuickSortRecursiveHoare;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.recursive.QuickSortRecursiveLomuto;
import pl.edu.pw.ee.aisd2025zex1.sorters.referencesort.ReferenceAlgSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.SelectionSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators;

public abstract class PerformanceChartsTest<T extends Comparable<T>> {

    private static final Logger LOG = Logger.getLogger(PerformanceChartsTest.class.getName());
    private String resultFilename;

    abstract T[] createDataByType(int size);
    abstract String getDataTypeName();

    @Test
    public void runPerformanceChartTest_CmpSorters() {
        int step = 1000;
        int maxSize = 200_000;
//        int maxSize = 524288;
        T[] data;

        for (SortingCmp<T> sorter : getCmpSorters()) {
            for (int i = 0; i < maxSize; i += step) {
    //        for (int i = 1024; i <= maxSize; i *= 2) {

                data = (T[]) createDataByType(i);

                System.out.println("sorting " + sorter.getClass().getSimpleName() + ", data size = " + i);
                String sorterName = sorter.getClass().getSimpleName();
                resultFilename = sorterName + "_" + getDataTypeName() + "_cmp_sorter_charts_performance.txt";
                measureTimeAndSaveToFileCmpSorter(sorter, data);
                }
            }
    }

//    @Test
//    public void runPerformanceChartTest_DetailedQSortWithInsort() {
//        int dataSize = 1000;
//        T[] data;
//        QuickSortIterativeWithInSort<T> sorter = new QuickSortIterativeWithInSort<>();
//
//        int subProblemStep = 10;
//        int maxSubProblemSize = 1000;
//
//        System.out.println("dataSize" + "\t|\t" + "subProblemSize" + "\t|\t" + "time");
//        for (int i = 0; i <= maxSubProblemSize; i += subProblemStep) {
//            data = (T[]) createDataByType(dataSize);
//            sorter.setSubProblemSize(i);
//
//            String sorterName = sorter.getClass().getSimpleName();
//            resultFilename = sorterName + "_" + getDataTypeName() + "prob_size_cmp_sorter_charts_performance.txt";
//            long time = measureTimeForCmpSorter(sorter, data);
//            System.out.println(dataSize + "\t|\t" + i + "\t|\t" + time);
//        }
//    }

    private List<SortingCmp<T>> getCmpSorters() {
        List<SortingCmp<T>> arr = new ArrayList<>();
        arr.add(new InsertionSort<>());
        arr.add(new SelectionSort<>());
        arr.add(new QuickSortIterativeMedian3<>());
        arr.add(new QuickSortIterativeRandom<>());
        arr.add(new QuickSortIterativeWithInSort<>());
        arr.add(new MergeSort<>());
        arr.add(new HeapSort<>());
        arr.add(new ReferenceAlgSort<>());
        return arr;
    }

    private void createOrClearResultFile() {
        File resultFile = new File(resultFilename);

        try {
            resultFile.createNewFile();

            new FileWriter(resultFile, false).close();

        } catch (IOException e) {
            LOG.log(SEVERE, "Caught exception during creating file", e);
        }
    }

    private void measureTimeAndSaveToFileCmpSorter(SortingCmp<T> sorter, T[] data) {
        int n = data.length;

        long measuredTime = measureTimeForCmpSorter(sorter, data);

        saveToFile(n, measuredTime);
    }

    private long measureTimeForCmpSorter(SortingCmp<T> sorter, T[] data) {
        long start = System.nanoTime();

        sorter.sort(data);

        long timeResult = System.nanoTime() - start;

        return timeResult;
    }

    private void saveToFile(int size, long measuredTime) {
        try (
                FileWriter fWriter = new FileWriter(resultFilename, true); BufferedWriter writer = new BufferedWriter(fWriter)) {

            writer.append(format("%8d | %d\n", size, measuredTime));

        } catch (IOException e) {
            LOG.log(SEVERE, "Caught exception during writing to file: " + resultFilename, e);
        }
    }
}
