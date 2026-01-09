package pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex7.data.input.GraphUtils;
import pl.edu.pw.ee.aisd2025zex7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.ShortestPathsResult;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_W_GRAPH_5_5;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_W_GRAPH_6_6;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.ShortestPaths;
import static pl.edu.pw.ee.aisd2025zex7.shortestpaths.utils.AdvancedConstructors.createShorhestPathAlgorithm;

public abstract class GeneralShortestPathsTest {

    private final Class<? extends ShortestPaths> shortestPathsClass;
    private ShortestPaths shortestPathsInstance;

    private WeightedGraph graph;

    public GeneralShortestPathsTest(Class<? extends ShortestPaths> shortestPathsClass) {
        this.shortestPathsClass = shortestPathsClass;
    }

    public ShortestPaths getShortestPathsInstance() {
        return shortestPathsInstance;
    }

    @BeforeEach
    public void setup() {
        shortestPathsInstance = createShorhestPathAlgorithm(shortestPathsClass);
    }

    @Test
    public void should_ReturnMaxIntValue_When_PathNotFound() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_5_5);
        int srcId = 0;
        int destId = 4;

        // when
        ShortestPathsResult result = getShortestPathsInstance().findShortestPath(graph, srcId, destId);

        // then
        int pathFrom3To4 = 16;
        int expectedResult = Integer.MAX_VALUE + pathFrom3To4;
        assertThat(result.getMinDistance()).isEqualTo(expectedResult);
    }

    @Test
    public void should_ReturnMinDistance_When_PathExist() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_6_6);
        int srcId = 0;
        int destId = 3;

        // when
        ShortestPathsResult result = shortestPathsInstance.findShortestPath(graph, srcId, destId);

        // then
        int expectedResult = 10;
        assertThat(result.getMinDistance()).isEqualTo(expectedResult);
    }

    @Test
    public void should_ReturnAllMinDistances_When_PathsExists() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_6_6);
        int srcId = 0;
        int destId = 3;

        // when
        ShortestPathsResult result = shortestPathsInstance.findShortestPath(graph, srcId, destId);

        // then
        int[] expectedDistances = {0, 2, 5, 10, 4, 10};
        assertThat(result.getDistances()).containsExactly(expectedDistances);
    }

}
