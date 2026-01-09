package pl.edu.pw.ee.aisd2025zex4.performance.chart;

import pl.edu.pw.ee.aisd2025zex4.performance.DataGenerator;
import pl.edu.pw.ee.aisd2025zex4.service.MapInterface;

public class RbtMapAscDataTreeHeightTest extends RbtMapTreeHeightTest {

    private static final String FILE_NAME_RAND = "rbtAscDataTreeHeightResults.txt";
    private final String[] data;

    public RbtMapAscDataTreeHeightTest() {
        super(FILE_NAME_RAND);
        data = new DataGenerator().generateAscData(MAX_SIZE);
    }

    @Override
    void putData(MapInterface<String, Integer> map, int currentSize, int step) {
        int end = currentSize + step;

        for (int i = currentSize; i < end && i < MAX_SIZE; i++) {
            map.setValue(data[i], i);
        }
    }

}
