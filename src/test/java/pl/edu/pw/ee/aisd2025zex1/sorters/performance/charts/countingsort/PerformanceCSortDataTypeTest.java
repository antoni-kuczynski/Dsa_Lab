package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.countingsort;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.countingsort.CountingSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements.QuickSortIterativeWithInSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators;

public class PerformanceCSortDataTypeTest {

    @Test
    public void measure() {
        int dataSizeStart = 100_000;
        int dataSizeStep = 100_000;
        int dataSizeEnd = 10_000_000;

        int[] data;
        for (int i = dataSizeStart; i <= dataSizeEnd; i += dataSizeStep) {
            CountingSort sorter = new CountingSort(dataSizeEnd);
            data = Generators.createRepeatingRandomData(i);

            long time = System.nanoTime();
            sorter.sort(data);
            long deltaTime = System.nanoTime() - time;

            System.out.println(CountingSort.class.getSimpleName() + "\t|\t" + i + "\t|\t" + deltaTime);
        }

        for (int i = dataSizeStart; i <= dataSizeEnd; i += dataSizeStep) {
            CountingSort sorter = new CountingSort(dataSizeEnd);
            data = Generators.createNonRepeatingRandomData(i);

            long time = System.nanoTime();
            sorter.sort(data);
            long deltaTime = System.nanoTime() - time;

            System.out.println(CountingSort.class.getSimpleName() + "\t|\t" + i + "\t|\t" + deltaTime);
        }
    }
}
