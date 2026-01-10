package pl.edu.pw.ee.aisd2025zex7.graphsearch.bfs;

import pl.edu.pw.ee.aisd2025zex7.data.input.NodeColor;
import pl.edu.pw.ee.aisd2025zex7.graphsearch.common.MazeTxtWayOutSearcher;

import java.util.ArrayDeque;
import java.util.Arrays;

public class BfsMazeTxtWayOutSearcher extends MazeTxtWayOutSearcher {

    //PL = przeszukiwanie wszerz
    //returns length of path in maze
    @Override
    protected int findWayOutOfMaze(int[][] maze, int startX, int startY) {
        NodeColor[][] colors = createColorArray(maze.length, maze[0].length);
        int visitedFields = 1;

        colors[startY][startX] = NodeColor.GRAY;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {startY, startX});

        while (!queue.isEmpty()) {
            int[] u = queue.poll();
            int[][] adj = getAdjacentFields(u[0], u[1]);

            for (int[] v : adj) {
                int adjX = v[0];
                int adjY = v[1];

                if (adjX < 0 || adjX > maze.length - 1) {
                    continue;
                }

                if (adjY < 0 || adjY > maze[0].length - 1) {
                    continue;
                }

                if (maze[adjX][adjY] != 0) {
                    continue;
                }

                if (colors[adjX][adjY] != NodeColor.WHITE) {
                    continue;
                }

                colors[adjX][adjY] = NodeColor.GRAY;
//                distance[adjX][adjY] = visitedFields;

                if (isBorder(maze, adjX, adjY)) {
//                    return distance[adjX][adjY];
                    return visitedFields;
                }

                visitedFields++;
                queue.add(v);
            }
            colors[u[0]][u[1]] = NodeColor.BLACK;

        }
        return -1;

    }
}
