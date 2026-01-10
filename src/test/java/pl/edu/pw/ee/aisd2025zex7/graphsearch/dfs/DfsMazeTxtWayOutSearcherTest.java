package pl.edu.pw.ee.aisd2025zex7.graphsearch.dfs;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.*;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.utils.MazeTxtWayOutSearcherTest;

public class DfsMazeTxtWayOutSearcherTest extends MazeTxtWayOutSearcherTest {

    public DfsMazeTxtWayOutSearcherTest() {
        super(new DfsMazeTxtWayOutSearcher());
    }

    @Override
    @Test
    public void should_PassCorrectly_When_InputFileIs_Maze_21x21() {
        // given
        String pathToMazeFile = PATH_MAZE_21_21;

        int startX = 1;
        int startY = 0;

        // when
        int resultPathLength = mazeSearcher.findWayOutOfMaze(pathToMazeFile, startX, startY);

        // then
        int expectedLength = 197;

        assertThat(resultPathLength)
                .isEqualTo(expectedLength);
    }

    @Override
    @Test
    public void should_PassCorrectly_When_InputFileIs_Maze_201x201() {
        // given
        String pathToMazeFile = PATH_MAZE_201_201;

        int startX = 1;
        int startY = 0;

        // when
        int resultPathLength = mazeSearcher.findWayOutOfMaze(pathToMazeFile, startX, startY);

        // then
        int expectedLength = 14145;

        assertThat(resultPathLength)
                .isEqualTo(expectedLength);
    }

    @Test
    public void should_PassCorrectly_When_InputFileIs_Maze_5x5() {
        // given
        String pathToMazeFile = PATH_MAZE_5_5;

        int startX = 3;
        int startY = 0;

        // when
        int resultPathLength = mazeSearcher.findWayOutOfMaze(pathToMazeFile, startX, startY);

        // then
        int expectedLength = 7;

        assertThat(resultPathLength)
                .isEqualTo(expectedLength);
    }
}
