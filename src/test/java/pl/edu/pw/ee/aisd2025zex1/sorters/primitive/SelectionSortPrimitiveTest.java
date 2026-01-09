package pl.edu.pw.ee.aisd2025zex1.sorters.primitive;

import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.SelectionSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortPrimitiveIntTest;

public class SelectionSortPrimitiveTest extends GeneralSortPrimitiveIntTest {
    public SelectionSortPrimitiveTest() {
        super(new SelectionSort<>());
    }
}
