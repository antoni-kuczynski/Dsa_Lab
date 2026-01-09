package pl.edu.pw.ee.aisd2025zex7.performance.shortestpaths;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex7.data.input.GraphUtils;
import pl.edu.pw.ee.aisd2025zex7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.ShortestPaths;

import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_10642_10642;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_1433_1433;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_1621_1621;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_18712_18712;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_19780_19780;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_20_20;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_27495_27495;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_30_30;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_3243_3243;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_3403_3403;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_52928_52928;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_5812_5812;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_6117_6117;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_7663_7663;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_980_980;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_9847_9847;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPerformancePathsToFiles.PATH_W_GRAPH_9882_9882;

public abstract class ShortestPathsPerformanceTest {

    private static final Logger LOG = Logger.getLogger(ShortestPathsPerformanceTest.class.getName());

    private final ShortestPaths algorithmInstance;

    public ShortestPathsPerformanceTest(ShortestPaths algorithmInstance) {
        this.algorithmInstance = algorithmInstance;
    }

    @Test
    public void meassureTimeOfAllData() {
        String[] pathsToGraphs = getDataPaths();
        long measuredTime;

        for (String pathToGraph : pathsToGraphs) {
            measuredTime = readGraphAndMeasureTime(pathToGraph);
            logResult(pathToGraph, measuredTime);
        }

    }

    private String[] getDataPaths() {
        String[] paths = {
            PATH_W_GRAPH_30_30,
            PATH_W_GRAPH_1433_1433,
            PATH_W_GRAPH_10642_10642,
            PATH_W_GRAPH_27495_27495,
            PATH_W_GRAPH_20_20,
            PATH_W_GRAPH_5812_5812,
            PATH_W_GRAPH_18712_18712,
            PATH_W_GRAPH_52928_52928,
            PATH_W_GRAPH_3243_3243,
            PATH_W_GRAPH_3403_3403,
            PATH_W_GRAPH_19780_19780,
            PATH_W_GRAPH_980_980,
            PATH_W_GRAPH_1621_1621,
            PATH_W_GRAPH_6117_6117,
            PATH_W_GRAPH_7663_7663,
            PATH_W_GRAPH_9847_9847,
            PATH_W_GRAPH_9882_9882
        };

        return paths;
    }

    private long readGraphAndMeasureTime(String pathToGraph) {
        WeightedGraph graph = new GraphUtils().readWeightedMatrix(pathToGraph);
        int srcId = 0;
        int destId = graph.getNumOfVertices() - 1;

        long measuredTime = measureTimeForSingleGraph(graph, srcId, destId);

        return measuredTime;
    }

    private long measureTimeForSingleGraph(WeightedGraph graph, int srcId, int destId) {
        long startTime = currentTimeMillis();

        algorithmInstance.findShortestPath(graph, srcId, destId);

        long measuredTime = currentTimeMillis() - startTime;

        return measuredTime;
    }

    private void logResult(String pathToGraph, long measuredTime) {
        String clsName = this.getClass().getSimpleName();

        String message = format("Measured time by \t %s \t | Data: %s \t | Time: %d",
                clsName, pathToGraph, measuredTime);

        LOG.info(message);
        System.out.println(message);
    }

}
