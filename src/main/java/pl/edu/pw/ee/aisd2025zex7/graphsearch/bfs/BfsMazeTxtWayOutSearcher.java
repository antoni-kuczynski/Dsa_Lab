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
        NodeColor[][] colors = new NodeColor[maze.length][maze[0].length];
//        int[][] distance = new int[maze.length][maze[0].length];
        int visitedFields = 1;


        for (NodeColor[] color : colors) {
            Arrays.fill(color, NodeColor.WHITE);
        }

        colors[startY][startX] = NodeColor.GRAY;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {startY, startX});

        while (!queue.isEmpty()) {
            int[] u = queue.poll();

            int[] left = {u[0] - 1, u[1]};
            int[] right = {u[0] + 1, u[1]};
            int[] bottom = {u[0], u[1] - 1};
            int[] top = {u[0], u[1] + 1};

            int[][] adj = {left, right, bottom, top};

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

                if (isBorder(adjX, adjY, maze)) {
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


    private boolean isBorder(int x, int y, int[][] maze) {
        return x == 0 || y == 0
                || x == maze.length - 1
                || y == maze[0].length - 1;
    }

    private int countFreeNeighbours(int x, int y, int[][] maze) {
        int count = 0;

        int[][] dirs = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx >= 0 && nx < maze.length
                    && ny >= 0 && ny < maze[0].length
                    && maze[nx][ny] == 0) {
                count++;
            }
        }
        return count;
    }




}
