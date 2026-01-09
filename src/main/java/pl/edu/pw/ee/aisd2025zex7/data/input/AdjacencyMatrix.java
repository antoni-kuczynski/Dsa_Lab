package pl.edu.pw.ee.aisd2025zex7.data.input;

import java.io.BufferedReader;
import java.util.concurrent.atomic.AtomicInteger;
import static pl.edu.pw.ee.aisd2025zex7.data.input.GraphType.UNDIRECTED;

public class AdjacencyMatrix extends GraphMatrix {

    AdjacencyMatrix(String pathToGraphDataFile) {
        super(pathToGraphDataFile);
    }

    @Override
    void fillAdjacencyMatrix(BufferedReader reader, GraphType graphType) {
        int lineId = 3;
        AtomicInteger lineCounter = new AtomicInteger(lineId);

        int expectedNumOfData = 2;

        reader.lines().forEach(line -> {
            int[] data = parseLine(line, lineCounter.getAndIncrement(), expectedNumOfData);
            int srcId = data[0];
            int dstId = data[1];

            setEdge(srcId, dstId);

            if (graphType.equals(UNDIRECTED)) {
                setEdge(dstId, srcId);
            }
        });
    }

}
