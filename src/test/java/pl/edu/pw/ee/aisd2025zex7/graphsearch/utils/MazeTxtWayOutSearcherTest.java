package pl.edu.pw.ee.aisd2025zex7.graphsearch.utils;

import java.io.FileNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_MAZE_21_21_INVALID;
import static pl.edu.pw.ee.aisd2025zex7.utils.ConstPathsToFiles.PATH_MAZE_5_5;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.common.MazeTxtWayOutSearcher;

public abstract class MazeTxtWayOutSearcherTest {

    protected MazeTxtWayOutSearcher mazeSearcher;

    public MazeTxtWayOutSearcherTest(MazeTxtWayOutSearcher mazeSearcher) {
        this.mazeSearcher = mazeSearcher;
    }

    @Test
    public void should_ThrowException_When_PathToFileIsNull() {
        // given
        String pathToMazeFile = null;
        int startX = 1;
        int startY = 0;

        // when
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            mazeSearcher.findWayOutOfMaze(pathToMazeFile, startX, startY);
        });

        // then
        assertThat(e)
                .hasMessage("The path to the maze file cannot be null!");
    }

    @Test
    public void should_ThrowException_When_PathToFileNotExist() {
        // given
        String pathToMazeFile = "./path/to/file/does/not/exist/unknown_maze.txt";
        int startX = 1;
        int startY = 0;

        // when
        Exception e = assertThrows(RuntimeException.class, () -> {
            mazeSearcher.findWayOutOfMaze(pathToMazeFile, startX, startY);
        });

        // then
        assertThat(e)
                .hasCauseInstanceOf(FileNotFoundException.class)
                .hasMessage("[ERROR] Unable to read maze data file.");
    }

    @Test
    public void should_Return_Negative1_WhenTheresNoExit() {
        // given
        String pathToMazeFile = PATH_MAZE_21_21_INVALID;

        int startX = 1;
        int startY = 0;

        // when
        int resultPathLength = mazeSearcher.findWayOutOfMaze(pathToMazeFile, startX, startY);

        // then
        int expectedLength = -1;

        assertThat(resultPathLength)
                .isEqualTo(expectedLength);
    }

    public abstract void should_PassCorrectly_When_InputFileIs_Maze_21x21();

    public abstract void should_PassCorrectly_When_InputFileIs_Maze_201x201();
}
