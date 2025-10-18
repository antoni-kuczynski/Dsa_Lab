package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createAscendingData;

public class PerformanceChartsAscDataTest<T extends Comparable<T>> extends PerformanceChartsTest<T> {

    @Override
    T[] createDataByType(int size) {
        return (T[]) createAscendingData(size);
    }

    @Override
    String getDataTypeName() {
        return "ascending_data";
    }
}
