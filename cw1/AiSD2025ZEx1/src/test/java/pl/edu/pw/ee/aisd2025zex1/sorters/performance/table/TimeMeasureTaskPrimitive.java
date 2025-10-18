package pl.edu.pw.ee.aisd2025zex1.sorters.performance.table;

import static java.lang.String.format;
import java.util.Arrays;
import java.util.logging.Logger;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

public class TimeMeasureTaskPrimitive implements Runnable {

    private static final Logger LOG = Logger.getLogger(TimeMeasureTaskPrimitive.class.getName());

    private final Sorting sorter;
    private final int[] orgDataToSort;

    private long averageTime;

    public TimeMeasureTaskPrimitive(Sorting sorter, int[] dataToSort) {
        this.sorter = sorter;
        this.orgDataToSort = dataToSort;
        this.averageTime = -1;
    }

    public long getAverageTime() {
        return averageTime;
    }

    @Override
    public void run() {
        try {
            averageTime = measureAvgTimeOfSorting();
        } catch (Exception e) {
            String message = format("Error during TimeMeasureTaskInt.run() [message: %s].", e.toString());
            LOG.severe(message);
        }
    }

    private long measureAvgTimeOfSorting() {
        long[] timeResults = measureTimeInLoop();
        return countAvgWithoutTenOutliers(timeResults);
    }

    private long[] measureTimeInLoop() {
        int nOfRepeat = 100;
        long[] timeResults = new long[nOfRepeat];

        int n = orgDataToSort.length;
        int[] dataToSort = new int[n];

        for (int i = 0; i < nOfRepeat; i++) {
            System.arraycopy(orgDataToSort, 0, dataToSort, 0, n);
            timeResults[i] = measureTimeOfSingleSorting(sorter, dataToSort);
        }

        return timeResults;
    }

    private long measureTimeOfSingleSorting(Sorting sorter, int[] dataToSort) {
        long start = System.nanoTime();
        sorter.sort(dataToSort);
        return System.nanoTime() - start;
    }

    private long countAvgWithoutTenOutliers(long[] timeResults) {
        Arrays.sort(timeResults);

        int nOfOutliers = 10;
        int nOfResults = timeResults.length;
        int start = nOfOutliers;
        int end = nOfResults - nOfOutliers;
        int n = nOfResults - 2 * nOfOutliers;

        assert start < end;

        long avgResult = 0;
        for (int i = start; i < end; i++) {
            avgResult += timeResults[i];
        }

        return avgResult / n;
    }
}
