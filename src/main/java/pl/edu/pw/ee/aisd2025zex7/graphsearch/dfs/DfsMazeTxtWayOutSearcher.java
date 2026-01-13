package pl.edu.pw.ee.aisd2025zex7.graphsearch.dfs;

import pl.edu.pw.ee.aisd2025zex7.data.input.NodeColor;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.common.MazeTxtWayOutSearcher;

public class DfsMazeTxtWayOutSearcher extends MazeTxtWayOutSearcher {
    private int visitedFields = 0;
    private int[][] order;
    private int startX;
    private int startY;

    private int[] FIELD_NON_EXIT = {-1,-1};

    @Override
    protected int findWayOutOfMaze(int[][] maze, int startX, int startY) {
        NodeColor[][] colors = createColorArray(maze.length, maze[0].length);
        order = new int[maze.length][maze[0].length];
        this.startX = startX;
        this.startY = startY;

        int[] type = dfsVisit(maze, colors, new int[] {startY, startX}, new int[] {startY, startX});
        if (type != FIELD_NON_EXIT) {
            return order[type[0]][type[1]];
        } else {
            return -1;
        }
    }


    private int[] dfsVisit(int[][] maze, NodeColor[][] colors, int[] current, int[] previous) {
        int xCurrent = current[0];
        int yCurrent = current[1];

//        visitedFields++;
//        order[xCurrent][yCurrent] = visitedFields;
        order[xCurrent][yCurrent] = order[previous[0]][previous[1]] + 1;
        colors[xCurrent][yCurrent] = NodeColor.GRAY;

        int[][] adjArray = getAdjacentFields(xCurrent, yCurrent);
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
                int[] field = dfsVisit(maze, colors, new int[] {adjX, adjY}, new int[] {xCurrent, yCurrent});
                if (field != FIELD_NON_EXIT)
                    return field;
            }
        }
        colors[xCurrent][yCurrent] = NodeColor.BLACK;
        if (isBorder(maze, xCurrent, yCurrent) && (yCurrent != startX && xCurrent != startY) && maze[xCurrent][yCurrent] == 0) {
            return new int[] {xCurrent, yCurrent};
        } else {
            return FIELD_NON_EXIT;
//            return FieldType.NORMAL;
        }
    }

//    enum FieldType {
//        NORMAL,
//        EXIT
//    }
}
