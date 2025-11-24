package pl.edu.pw.ee.aisd2025zex4.performance.chart;

import pl.edu.pw.ee.aisd2025zex4.performance.DataGenerator;
import pl.edu.pw.ee.aisd2025zex4.service.MapInterface;

public class RbtMapDescDataTreeHeightTest extends RbtMapTreeHeightTest {

    private static final String FILE_NAME_RAND = "rbtDescDataTreeHeightResults.txt";
    private final String[] data;

    public RbtMapDescDataTreeHeightTest() {
        super(FILE_NAME_RAND);
        data = new DataGenerator().generateDescData(MAX_SIZE);
    }

    @Override
    void putData(MapInterface<String, Integer> map, int currentSize, int step) {
        int end = currentSize + step;

        for (int i = currentSize; i < end && i < MAX_SIZE; i++) {
            map.setValue(data[i], i);
        }
    }

}
