package pl.edu.pw.ee.aisd2025zex1.sorters.performance.table;

import pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators;

public class PerformanceRandDataPrimitiveTest extends PerformancePrimitiveTest{
    @Override
    protected int[] generateData(int size) {
        return Generators.createRandomDataIntPrimitive(size);
    }
}
