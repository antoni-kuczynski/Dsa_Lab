package pl.edu.pw.ee.aisd2025zex7.graphsearch.bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import pl.edu.pw.ee.aisd2025zex7.data.input.Graph;

import pl.edu.pw.ee.aisd2025zex7.data.outcome.GraphBfsResult;
import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.services.GraphSearcher;

public class BreadthFirstSeaerch implements GraphSearcher {

    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    private final int initPrevVal = -1;

    private Deque<Integer> queue;
    private int[] color;
    private int[] prev;
    private int[] dist;

    private Graph graph;

    @Override
    public GraphBfsResult searchGraphPaths(Graph graph, int startId) {
        validateInput(graph, startId);

        initData(graph);

        processFirstVertice(startId);
        processAllRestVertices();

        GraphBfsResult result = new GraphBfsResult(startId, prev, dist);

        return result;
    }

    private void validateInput(Graph graph, int startId) {
        if (isNull(graph)) {
            throw new IllegalArgumentException("Input graph cannot be null!");
        }

        if (startId < 0) {
            throw new IllegalArgumentException("Graph start id cannot be lower than \"0\"!");
        }
    }

    private void initData(Graph graph) {
        this.graph = graph;
        int nVertices = graph.getNumOfVertices();

        color = new int[nVertices];
        prev = new int[nVertices];
        dist = new int[nVertices];

        for (int i = 0; i < nVertices; i++) {
            color[i] = WHITE;
            prev[i] = initPrevVal;
            dist[i] = Integer.MAX_VALUE;
        }
    }

    private void processFirstVertice(int startId) {
        color[startId] = GRAY;
        dist[startId] = 0;
        queue = new ArrayDeque<>();

        queue.add(startId);
    }

    private void processAllRestVertices() {
        int currentVertice;

        while (!queue.isEmpty()) {
            currentVertice = queue.removeFirst();

            int[] neighbours = graph.getNeighbours(currentVertice);

            for (int neighbourId : neighbours) {

                if (isWhite(neighbourId)) {

                    dist[neighbourId] = dist[currentVertice] + 1;
                    prev[neighbourId] = currentVertice;
                    color[neighbourId] = GRAY;

                    queue.add(neighbourId);
                }
            }

            color[currentVertice] = BLACK;
        }
    }

    private boolean isWhite(int verticeId) {
        return color[verticeId] == WHITE;
    }

}
