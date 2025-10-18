package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createRandomDataDouble;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createRandomDataInteger;

public class PerformanceChartsRandomDataTest<T extends Comparable<T>> extends PerformanceChartsTest<T> {

    @Override
    T[] createDataByType(int size) {
        return (T[]) createRandomDataInteger(size);
    }

    @Override
    String getDataTypeName() {
        return "random_data";
    }
}
