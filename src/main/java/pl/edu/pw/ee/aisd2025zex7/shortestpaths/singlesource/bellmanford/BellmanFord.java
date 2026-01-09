package pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource.bellmanford;

import pl.edu.pw.ee.aisd2025zex7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.ShortestPathsResult;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.ShortestPaths;

public class BellmanFord extends ShortestPaths {

    private final int unknownVerticeId = -1;

    private int[] distance;

    private int[] prev;

    private WeightedGraph graph;

    @Override
    public ShortestPathsResult findShortestPath(WeightedGraph graph, int srcId, int destId) {
        validateInput(graph, srcId, destId);

        initData(graph);

        int n = graph.getNumOfVertices();

        distance[srcId] = 0;

        boolean failOnUpdate;
        boolean leaveEarly;
        int newDistance;
        int edgeWeight;

        for (int i = 1; i <= n; i++) {
            failOnUpdate = (i == n);
            leaveEarly = true;

            for (int verticeId = 0; verticeId < n; verticeId++) {
                for (int neighbourId : graph.getNeighbours(verticeId)) {

                    edgeWeight = graph.getWeight(verticeId, neighbourId);
                    newDistance = distance[verticeId] + edgeWeight;

                    if (newDistance < distance[neighbourId]) {
                        if (failOnUpdate) {
                            throw new RuntimeException("The graph has a negative cycle!");
                        }
                        distance[neighbourId] = newDistance;
                        prev[neighbourId] = verticeId;
                        leaveEarly = false;
                    }

                }
            }

            if (leaveEarly) {
                break;
            }
        }

        ShortestPathsResult result = new ShortestPathsResult(srcId, destId, distance[destId], distance);

        return result;

    }

    private void initData(WeightedGraph graph) {
        this.graph = graph;

        int n = graph.getNumOfVertices();
        int initVal = unknownVerticeId;

        distance = new int[n];
        prev = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = initVal;
        }
    }

}
