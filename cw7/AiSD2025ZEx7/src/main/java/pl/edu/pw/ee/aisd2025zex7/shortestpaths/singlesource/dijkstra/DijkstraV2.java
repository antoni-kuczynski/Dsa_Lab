package pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource.dijkstra;

import pl.edu.pw.ee.aisd2025zex7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.ShortestPathsResult;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.ShortestPaths;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource.dijkstra.utils.IndexedMinPQ;


public class DijkstraV2 extends ShortestPaths {

    private final int unknownVerticeId = -1;

    private WeightedGraph graph;

    private int[] distances;

    private int[] prev;

    private IndexedMinPQ queue;

    @Override
    public ShortestPathsResult findShortestPath(WeightedGraph graph, int srcId, int destId) {
        validateInput(graph, srcId, destId);

        initData(graph);

        distances[srcId] = 0;

        fillQueue(distances);

        int currentVerticeId;

        while (!queue.isEmpty()) {
            currentVerticeId = queue.poll();

            updateNearestDistances(currentVerticeId);
        }

        ShortestPathsResult result = new ShortestPathsResult(srcId, destId, distances[destId], distances);

        return result;
    }

    private void initData(WeightedGraph graph) {
        this.graph = graph;

        int n = graph.getNumOfVertices();
        int initVal = unknownVerticeId;

        queue = new IndexedMinPQ(n);
        distances = new int[n];
        prev = new int[n];

        for (int i = 0; i < n; i++) {
            distances[i] = Integer.MAX_VALUE;
            prev[i] = initVal;
        }
    }

    private void fillQueue(int[] dist) {
        int n = graph.getNumOfVertices();

        for (int verticeId = 0; verticeId < n; verticeId++) {
            queue.insert(verticeId, dist[verticeId]);
        }
    }

    private void updateNearestDistances(int currentVerticeId) {
        int newDistance;
        int edgeWeight;

        for (int neighbourId : graph.getNeighbours(currentVerticeId)) {
            edgeWeight = graph.getWeight(currentVerticeId, neighbourId);
            newDistance = distances[currentVerticeId] + edgeWeight;

            if (newDistance < distances[neighbourId]) {

                insertOrUpdateDistance(neighbourId, newDistance);

                distances[neighbourId] = newDistance;
                prev[neighbourId] = currentVerticeId;
            }
        }
    }

    private void insertOrUpdateDistance(int neighbourId, int newDistance) {
        if (queue.contains(neighbourId)) {
            queue.updatePriority(neighbourId, newDistance);
        } else {
            queue.insert(neighbourId, newDistance);
        }
    }
}