package pl.edu.pw.ee.aisd2025zex7.data.input;

import java.io.BufferedReader;
import static java.lang.String.format;
import java.util.concurrent.atomic.AtomicInteger;
import static pl.edu.pw.ee.aisd2025zex7.data.input.GraphType.UNDIRECTED;

public class WeightedAdjacencyMatrix extends GraphMatrix implements WeightedGraph {

    public WeightedAdjacencyMatrix(String pathToGraphDataFile) {
        super(pathToGraphDataFile);
    }

    @Override
    void fillAdjacencyMatrix(BufferedReader reader, GraphType graphType) {
        int lineId = 3;
        AtomicInteger lineCounter = new AtomicInteger(lineId);

        int expectedNumOfData = 3;

        reader.lines().forEach(line -> {
            int[] data = parseLine(line, lineCounter.getAndIncrement(), expectedNumOfData);
            int srcId = data[0];
            int dstId = data[1];
            int weight = data[2];

            validateEdgeWeight(weight);

            setWeight(srcId, dstId, weight);

            if (graphType.equals(UNDIRECTED)) {
                setWeight(dstId, srcId, weight);
            }
        });
    }

    @Override
    public int getWeight(int srcId, int destId) {
        int nCols = getMatrix()[0].length;

        if (srcId >= nCols || destId >= nCols) {
            throw new IllegalArgumentException("Vertice ID (src or dest) does not exist!");
        }

        return getMatrix()[srcId][destId];
    }

    private void validateEdgeWeight(int weight) {
        int minThreshold = 0;

        if (weight <= minThreshold) {
            throw new IllegalArgumentException(format("The edge weight must be greater than %s!", minThreshold));
        }
    }

}
