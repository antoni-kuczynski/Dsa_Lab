package pl.edu.pw.ee.aisd2025zex1.sorters.performance.table.countingsort;

import pl.edu.pw.ee.aisd2025zex1.sorters.performance.table.PerformancePrimitiveTest;
import pl.edu.pw.ee.aisd2025zex1.sorters.performance.table.PerformanceTest;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators;

public class PerformanceRandNonRepeatingDataTest extends PerformancePrimitiveTest {
    @Override
    protected int[] generateData(int size) {
        return Generators.createNonRepeatingRandomData(size);
    }
}
