package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.qsortinsort;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements.QuickSortIterativeWithInSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators;

public class PerformanceQsortInsortSubproblemTest {

    @Test
    public void measure() {
        QuickSortIterativeWithInSort<Double> sorter = new QuickSortIterativeWithInSort<>();
        int dataSize = 10_000;
        int subProblemStart = 0;
        int subProblemStep = 1;
        int subProblemEnd = 1000;

        Double[] data;
        for (int i = subProblemStart; i <= subProblemEnd; i += subProblemStep) {
            data = Generators.createRandomDataDouble(dataSize);
            sorter.setSubProblemSize(i);

            long time = System.nanoTime();
            sorter.sort(data);
            long deltaTime = System.nanoTime() - time;

            System.out.println(QuickSortIterativeWithInSort.class.getSimpleName() + "\t|\t" + i + "\t|\t" + deltaTime);
        }
    }
}
