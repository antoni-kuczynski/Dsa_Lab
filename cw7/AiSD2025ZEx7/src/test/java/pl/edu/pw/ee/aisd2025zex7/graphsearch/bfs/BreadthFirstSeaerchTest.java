package pl.edu.pw.ee.aisd2025zex7.graphsearch.bfs;

import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex7.data.input.Graph;
import pl.edu.pw.ee.aisd2025zex7.data.input.GraphUtils;
import pl.edu.pw.ee.aisd2025zex7.data.outcome.GraphBfsResult;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.services.GraphSearcher;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_GRAPH_9_9;

public class BreadthFirstSeaerchTest {

    private GraphSearcher graphSearcher;

    @BeforeEach
    public void setup() {
        graphSearcher = new BreadthFirstSeaerch();
    }

    @Test
    public void should_ReturnBfsResult_when_GraphIsDisconnected() {
        // given
        Graph graph = new GraphUtils().readMatrix(PATH_GRAPH_9_9);
        int startVerticeId = 0;

        // when
        GraphBfsResult result = (GraphBfsResult) graphSearcher.searchGraphPaths(graph, startVerticeId);

        // then
        assertThat(result.getDistance()).containsExactly(new int[]{0, 1, 2, 2, 1, 2, 3, MAX_VALUE, MAX_VALUE});
        assertThat(result.getPrevVertices()).containsExactly(new int[]{-1, 0, 1, 4, 0, 4, 2, -1, -1});
    }
}