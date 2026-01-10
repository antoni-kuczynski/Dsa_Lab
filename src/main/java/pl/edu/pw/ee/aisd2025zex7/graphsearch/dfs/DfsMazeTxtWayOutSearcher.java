package pl.edu.pw.ee.aisd2025zex7.graphsearch.dfs;

import pl.edu.pw.ee.aisd2025zex7.data.input.NodeColor;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.common.MazeTxtWayOutSearcher;

public class DfsMazeTxtWayOutSearcher extends MazeTxtWayOutSearcher {
    private int visitedFields = 0;
    private int[][] order;
    private int startX;
    private int startY;

    @Override
    protected int findWayOutOfMaze(int[][] maze, int startX, int startY) {
        NodeColor[][] colors = createColorArray(maze.length, maze[0].length);
        order = new int[maze.length][maze[0].length];
        this.startX = startX;
        this.startY = startY;

        FieldType type = dfsVisit(maze, colors, startY, startX);
        if (type == FieldType.EXIT) {
            return visitedFields;
        } else {
            return -1;
        }
    }


    private FieldType dfsVisit(int[][] maze, NodeColor[][] colors, int x, int y) {
        visitedFields++;
        order[x][y] = visitedFields;
        colors[x][y] = NodeColor.GRAY;

        int[][] adjArray = getAdjacentFields(x, y);
        for (int[] adj : adjArray) {
            int adjX = adj[0];
            int adjY = adj[1];

            if (adjX < 0 || adjX > maze.length - 1 || adjY < 0 || adjY > maze[0].length - 1) {
                continue;
            }

            if (maze[adjX][adjY] != 0) {
                continue;
            }

            if (colors[adjX][adjY] == NodeColor.WHITE) {
                FieldType field = dfsVisit(maze, colors, adjX, adjY);
                if (field == FieldType.EXIT)
                    return FieldType.EXIT;
            }
        }
        colors[x][y] = NodeColor.BLACK;
        if (isBorder(maze, x, y) && (y != startX && x != startY) && maze[x][y] == 0) {
            return FieldType.EXIT;
        } else {
            return FieldType.NORMAL;
        }
    }

    enum FieldType {
        NORMAL,
        EXIT
    }
}
