package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createDescendingData;

public class PerformanceChartsDescDataTest<T extends Comparable<T>> extends PerformanceChartsTest<T> {

    @Override
    T[] createDataByType(int size) {
        return (T[]) createDescendingData(size);
    }

    @Override
    String getDataTypeName() {
        return "descending_data";
    }
}
