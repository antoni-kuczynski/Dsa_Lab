package pl.edu.pw.ee.aisd2025zex6.lcs;

import java.util.Arrays;

public class LongestCommonSubsequence {

    private void validateParams(String left, String top) {
        if (left == null || top == null) {
            throw new IllegalArgumentException("Params cannot be null!");
        }
    }

    public String findLcs(String left, String top) {
        validateParams(left, top);

        int[][] arr = new int[left.length() + 1][top.length() + 1];
        int[][][] directions = new int[left.length() + 1][top.length() + 1][2];

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                int leftIndex = i - 1;
                int topIndex = j - 1;

                if (left.charAt(leftIndex) == top.charAt(topIndex)) {
                    arr[i][j] = arr[i-1][j-1] + 1;
                    directions[i][j] = new int[]{-1,-1};
                } else {
                    if (arr[i][j-1] > arr[i-1][j]) {
                        arr[i][j] = arr[i][j-1];
                        directions[i][j] = new int[]{0, -1};
                    } else {
                        arr[i][j] = arr[i-1][j];
                        directions[i][j] = new int[]{-1, 0};
                    }
                }

            }
        }

        int x = arr.length-1;
        int y = arr[0].length-1;
        char[] str = new char[arr[x][y]];
        int index = str.length - 1;

        while (x > 0 && y > 0 && index >= 0) {
            if (directions[x][y][0] == -1 && directions[x][y][1] == -1) {
                str[index--] = left.charAt(x-1);
            }

            x += directions[x][y][0];
            y += directions[x][y][1];
        }

        StringBuilder s = new StringBuilder();
        for (char c : str) {
            s.append(c);
        }
        return s.toString();
    }
    
}
