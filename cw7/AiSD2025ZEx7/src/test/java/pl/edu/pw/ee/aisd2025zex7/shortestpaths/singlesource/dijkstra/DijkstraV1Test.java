package pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource.dijkstra;

import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex7.data.input.GraphUtils;
import pl.edu.pw.ee.aisd2025zex7.data.input.WeightedGraph;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.ShortestPathsResult;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_W_GRAPH_5_5;
import pl.edu.pw.ee.aisd2025zex7.shortestpaths.singlesource.GeneralShortestPathsTest;

public class DijkstraV1Test extends GeneralShortestPathsTest {

    private WeightedGraph graph;

    public DijkstraV1Test() {
        super(DijkstraV1.class);
    }

    @Test
    @Override
    public void should_ReturnMaxIntValue_When_PathNotFound() {
        override_Should_ReturnUnknownValue_When_Path_Not_Found();
    }

    private void override_Should_ReturnUnknownValue_When_Path_Not_Found() {
        // given
        graph = new GraphUtils().readWeightedMatrix(PATH_W_GRAPH_5_5);
        int srcId = 0;
        int destId = 4;

        // when
        ShortestPathsResult result = getShortestPathsInstance().findShortestPath(graph, srcId, destId);

        // then
        int expectedResult = MAX_VALUE;
        assertThat(result.getMinDistance()).isEqualTo(expectedResult);
    }
}