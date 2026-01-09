package pl.edu.pw.ee.aisd2025zex7.graphsearch.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.util.Objects.isNull;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.services.MazeTxtSearcher;

public abstract class MazeTxtWayOutSearcher implements MazeTxtSearcher {

    private static final Logger LOG = Logger.getLogger(MazeTxtWayOutSearcher.class.getName());

    @Override
    public int findWayOutOfMaze(String pathToMazeTxtFile, int startX, int startY) {
        int[][] maze = readMaze(pathToMazeTxtFile);

        validateInput(maze, startX, startY);

        int resultPathLength = findWayOutOfMaze(maze, startX, startY);

        return resultPathLength;
    }

    protected abstract int findWayOutOfMaze(int[][] maze, int startX, int startY);

    private int[][] readMaze(String pathToMazeTxtFile) {
        validateInputPath(pathToMazeTxtFile);

        int[][] maze = null;

        try (FileReader fReader = new FileReader(pathToMazeTxtFile); //
                 BufferedReader reader = new BufferedReader(fReader)) {

            maze = fileToMazeArray(reader);

        } catch (FileNotFoundException e) {
            logAndThrow("[ERROR] Unable to read maze data file.", e);

        } catch (IOException e) {
            logAndThrow("[ERROR] An unexpected exception occurred while reading the maze data.", e);
        }

        return maze;
    }

    private void validateInputPath(String pathToMazeTxtFile) {
        if (isNull(pathToMazeTxtFile)) {
            throw new IllegalArgumentException("The path to the maze file cannot be null!");
        }
    }

    private void logAndThrow(String message, Throwable cause) {
        LOG.log(SEVERE, message);
        throw new RuntimeException(message, cause);
    }

    private void validateInput(int[][] maze, int startX, int startY) {
        validateMaze(maze);
        validateStartY(maze, startY);
        validateStartX(maze, startX);
    }

    private void validateMaze(int[][] maze) {
        if (isNull(maze)) {
            throw new RuntimeException("The maze data cannot be null!");
        }
    }

    private void validateStartY(int[][] maze, int startY) {
        int rows = maze.length;

        if (startY < 0 || startY >= rows) {
            throw new IllegalArgumentException("The Y coords must be in the range <0, nRows)!");
        }
    }

    private void validateStartX(int[][] maze, int startX) {
        int cols = maze[0].length;

        if (startX < 0 || startX >= cols) {
            throw new IllegalArgumentException("The X coords must be in the range <0, nCols)!");
        }
    }

    private int[][] fileToMazeArray(BufferedReader reader) throws IOException {
        int nRows = Integer.parseInt(reader.readLine());
        int nCols = Integer.parseInt(reader.readLine());
        int row = 0;
        int col;

        int[][] maze = new int[nRows][nCols];

        String line;

        while ((line = reader.readLine()) != null) {
            col = 0;

            for (int c : line.toCharArray()) {
                maze[row][col] = Character.getNumericValue(c);
                col++;
            }

            row++;
        }

        return maze;
    }

}
