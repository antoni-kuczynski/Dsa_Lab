package pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource.dijkstra;

import pl.edu.pw.ee.aisd2025zex7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.ShortestPathsResult;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.ShortestPaths;

public class DijkstraV1 extends ShortestPaths {

    private final int unknownVerticeId = -1;
    private final int initDistance = Integer.MAX_VALUE;

    private WeightedGraph graph;

    private int[] distances;

    private int[] prev;

    private boolean[] visited;

    @Override
    public ShortestPathsResult findShortestPath(WeightedGraph graph, int srcId, int destId) {
        validateInput(graph, srcId, destId);

        initData(graph);

        distances[srcId] = 0;

        int nearestVerticeId;

        while (true) {
            nearestVerticeId = findNearestNotVisitedVertice();

            if (nearestVerticeId == unknownVerticeId) {
                break;
            }

            visited[nearestVerticeId] = true;
            updateNearestDistances(nearestVerticeId);
        }

        ShortestPathsResult result = new ShortestPathsResult(srcId, destId, distances[destId], distances);

        return result;
    }

    private void initData(WeightedGraph graph) {
        this.graph = graph;

        int n = graph.getNumOfVertices();
        int initVal = unknownVerticeId;

        distances = new int[n];
        prev = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            distances[i] = initDistance;
            prev[i] = initVal;
            visited[i] = false;
        }
    }

    private int findNearestNotVisitedVertice() {
        int n = visited.length;

        int nearestId = unknownVerticeId;
        int minDistance = initDistance;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                nearestId = i;
            }
        }

        return nearestId;
    }

    private void updateNearestDistances(int currentVerticeId) {
        int newDistance;
        int edgeWeight;

        for (int neighbourId : graph.getNeighbours(currentVerticeId)) {

            edgeWeight = graph.getWeight(currentVerticeId, neighbourId);
            newDistance = distances[currentVerticeId] + edgeWeight;

            if (newDistance < distances[neighbourId]) {
                distances[neighbourId] = newDistance;
                prev[neighbourId] = currentVerticeId;
            }
        }
    }
}
