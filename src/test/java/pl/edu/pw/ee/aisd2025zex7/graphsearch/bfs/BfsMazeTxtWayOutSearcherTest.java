package pl.edu.pw.ee.aisd2025zex7.graphsearch.bfs;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.*;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.utils.MazeTxtWayOutSearcherTest;

public class BfsMazeTxtWayOutSearcherTest extends MazeTxtWayOutSearcherTest {

    public BfsMazeTxtWayOutSearcherTest() {
        super(new BfsMazeTxtWayOutSearcher());
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
        int expectedLength = 171;
//        assert false; // TODO: validate and fix expectedLength

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
        int expectedLength = 17_754;

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
        int expectedLength = 8;

        assertThat(resultPathLength)
                .isEqualTo(expectedLength);
    }

}
