package pl.edu.pw.ee.aisd2025zex7.shortestpaths;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex7.data.input.Graph;
import pl.edu.pw.ee.aisd2025zex7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.ShortestPathsResult;

public abstract class ShortestPaths {

    public abstract ShortestPathsResult findShortestPath(WeightedGraph graph, int srcId, int destId);

    protected void validateInput(Graph graph, int srcId, int destId) {
        if (isNull(graph)) {
            throw new IllegalArgumentException("Input graph cannot be null!");
        }

        int n = graph.getNumOfVertices();

        if (srcId >= n || srcId < 0) {
            throw new IllegalArgumentException("The starting index should be positive and less than num of vertices!");

        } else if (destId >= n || destId < 0) {
            throw new IllegalArgumentException("The ending index should be positive and lower than num of vertices!");
        }
    }
}
