//package pl.edu.pw.ee.aisd2025zex1.sorters.performance.table;
//
//import org.junit.jupiter.api.Test;
//import pl.edu.pw.ee.aisd2025zex1.services.Sorting;
//import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
//import pl.edu.pw.ee.aisd2025zex1.sorters.insort.InsertionSort;
//import pl.edu.pw.ee.aisd2025zex1.sorters.referencesort.ReferenceAlgSort;
//import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.SelectionSort;
//import pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.logging.Logger;
//
//import static java.lang.String.format;
//import static java.util.logging.Level.SEVERE;
//import static java.util.logging.Level.WARNING;
//
//public abstract class PerformancePrimitiveTest<T extends Comparable<T>> {
//
//    private static final Logger LOG = Logger.getLogger(PerformancePrimitiveTest.class.getName());
//
//    @Test
//    public void runPerformanceTest() {
//        List<Sorting> sorters = getListOfSorters();
//        int[] dataSizes = getDataSize();
//
//        measureAndPrintAvgTimeOfAllSorters(sorters, dataSizes);
//    }
//
//    protected abstract T[] generateData(int size);
//
//    private List<Sorting> getListOfSorters() {
//        List<Sorting> sorters = new ArrayList<>();
//
//        sorters.add(new InsertionSort());
//        sorters.add(new ReferenceAlgSort<>());
//        sorters.add(new SelectionSort<>());
////        sorters.add(new SelectionSort());
////        sorters.add(new QuickSortRecursiveHoare());
////        sorters.add(new QuickSortIterativeHoare());
////        sorters.add(new QuickSortIterativeLomuto<>());
////        sorters.add(new QuickSortIterativeRandom());
////        sorters.add(new QuickSortIterativeWithInSort());
////        sorters.add(new QuickSortIterativeMedian3());
////        sorters.add(new MergeSort());
////        sorters.add(new HeapSort());
//        // TODO: rest of algorithms
//        return sorters;
//    }
//
//    private int[] getDataSize() {
////        int n = 7;
//        int n = 9;
//        int startPower = 11;
//        int[] dataSizes = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            dataSizes[i] = (int) (Math.pow(2, (startPower + i)));
//        }
//
//        return dataSizes;
//    }
//
//    private void measureAndPrintAvgTimeOfAllSorters(List<Sorting> sorters, int[] dataSizes) {
//        int n = dataSizes.length;
//
//        for (Sorting sorter : sorters) {
//            for (int size : dataSizes) {
//
//                int[] dataToSort = Generators.createRandomDataIntPrimitive(size);
//
//                measureTimeAndPrintResultsOfSorting(sorter, dataToSort);
//            }
//        }
//    }
//
//    private void measureTimeAndPrintResultsOfSorting(Sorting sorter, int[] dataToSort) {
//        String sorterName = getSorterName(sorter);
//        int size = dataToSort.length;
//
//        double avgTimeResult = measureAvgTimeAndStopAfterDuration(sorter, dataToSort);
//
//        System.out.println(format("%20s | %7d | %g", sorterName, size, avgTimeResult));
//    }
//
//    private String getSorterName(Sorting sorter) {
//        String result = sorter.getClass().getSimpleName();
//
//        return result;
//    }
//
//    private double measureAvgTimeAndStopAfterDuration(Sorting sorter, int[] dataToSort) {
//        TimeMeasureTaskPrimitive timeMeasureTask = new TimeMeasureTaskPrimitive(sorter, dataToSort);
//
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future future = executor.submit(timeMeasureTask);
//        long maxDurationInSeconds = 5;
//
//        try {
//            future.get(maxDurationInSeconds, TimeUnit.SECONDS);
//
//        } catch (TimeoutException e) {
//            future.cancel(true);
//            LOG.log(WARNING, "A TIMEOUT interrupt was caught during average time measurement: {0}", e.getClass().getName());
//
//        } catch (InterruptedException | ExecutionException e) {
//            if (e.getCause() instanceof StackOverflowError) {
//                logSevere("Caught StackOverflow!");
//            } else {
//                LOG.log(WARNING, "An exception was caught while measuring the average time: {0}", e.getClass().getName());
//            }
//
//        } finally {
//            executor.shutdown();
//        }
//
//        double result = timeMeasureTask.getAverageTime();
//
//        return result;
//    }
//
//    private void logSevere(String message) {
//        String redColorPrefix = "\u001B[31m";
//        String redColoerSuffix = "\u001B[0m";
//
//        message = redColorPrefix + message + redColoerSuffix;
//
//        LOG.log(SEVERE, message);
//    }
//
//}

package pl.edu.pw.ee.aisd2025zex1.sorters.performance.table;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;
import pl.edu.pw.ee.aisd2025zex1.sorters.countingsort.CountingSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.insort.InsertionSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.referencesort.ReferenceAlgSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.SelectionSort;

public abstract class PerformancePrimitiveTest {

    private static final Logger LOG = Logger.getLogger(PerformancePrimitiveTest.class.getName());

    @Test
    public void runPerformanceTest() {
        List<Sorting> sorters = getListOfSorters();
        int[] dataSizes = getDataSize();

        measureAndPrintAvgTimeOfAllSorters(sorters, dataSizes);
    }

    protected abstract int[] generateData(int size);

    private List<Sorting> getListOfSorters() {
        List<Sorting> sorters = new ArrayList<>();

//        sorters.add(new InsertionSort());
//        sorters.add(new ReferenceAlgSort());
//        sorters.add(new SelectionSort());
        sorters.add(new CountingSort(40_100_000));
        return sorters;
    }

    private int[] getDataSize() {
        int n = 18;
        int startPower = 10;
        int[] dataSizes = new int[n];

        for (int i = 0; i < n; i++) {
            dataSizes[i] = (int) Math.pow(2, startPower + i);
        }

        return dataSizes;
    }

    private void measureAndPrintAvgTimeOfAllSorters(List<Sorting> sorters, int[] dataSizes) {
        for (Sorting sorter : sorters) {
            for (int size : dataSizes) {
                int[] dataToSort = generateData(size);
                measureTimeAndPrintResultsOfSorting(sorter, dataToSort);
            }
        }
    }

    private void measureTimeAndPrintResultsOfSorting(Sorting sorter, int[] dataToSort) {
        String sorterName = getSorterName(sorter);
        int size = dataToSort.length;

        double avgTimeResult = measureAvgTimeAndStopAfterDuration(sorter, dataToSort);

        System.out.println(format("%20s | %7d | %g", sorterName, size, avgTimeResult));
    }

    private String getSorterName(Sorting sorter) {
        return sorter.getClass().getSimpleName();
    }

    private double measureAvgTimeAndStopAfterDuration(Sorting sorter, int[] dataToSort) {
        TimeMeasureTaskPrimitive timeMeasureTask = new TimeMeasureTaskPrimitive(sorter, dataToSort);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(timeMeasureTask);
        long maxDurationInSeconds = 5;

        try {
            future.get(maxDurationInSeconds, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            future.cancel(true);
            LOG.log(WARNING, "A TIMEOUT interrupt was caught during average time measurement: {0}", e.getClass().getName());

        } catch (InterruptedException | ExecutionException e) {
            if (e.getCause() instanceof StackOverflowError) {
                logSevere("Caught StackOverflow!");
            } else {
                LOG.log(WARNING, "An exception was caught while measuring the average time: {0}", e.getClass().getName());
            }

        } finally {
            executor.shutdown();
        }

        return timeMeasureTask.getAverageTime();
    }

    private void logSevere(String message) {
        String redColorPrefix = "\u001B[31m";
        String redColorSuffix = "\u001B[0m";

        message = redColorPrefix + message + redColorSuffix;
        LOG.log(SEVERE, message);
    }
}
