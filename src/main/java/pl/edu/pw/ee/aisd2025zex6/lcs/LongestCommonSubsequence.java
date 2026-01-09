package pl.edu.pw.ee.aisd2025zex6.lcs;

public class LongestCommonSubsequence {

    private void validateParams(String left, String top) {
        if (left == null || top == null) {
            throw new IllegalArgumentException("Params cannot be null!");
        }

        if (left.length() > Integer.MAX_VALUE - 1 || top.length() > Integer.MAX_VALUE - 1) {
            throw new IllegalArgumentException("Strings length cannot be higher than Integer.MAX_VALUE - 1!");
        }
    }

    public String findLcs(String left, String top) {
        validateParams(left, top);

        int[][] lcsTable = new int[left.length() + 1][top.length() + 1];
        char[][] directionTable = new char[left.length() + 1][top.length() + 1];

        //U - up
        //L - left
        //D - diagonal

        for (int i = 1; i <= left.length(); i++) {
            for (int j = 1; j <= top.length(); j++) {
                if (left.charAt(i - 1) == top.charAt(j - 1)) {
                    lcsTable[i][j] = lcsTable[i - 1][j - 1] + 1;
                    directionTable[i][j] = 'D';
                } else if (lcsTable[i - 1][j] >= lcsTable[i][j - 1]) {
                    lcsTable[i][j] = lcsTable[i - 1][j];
                    directionTable[i][j] = 'U';
                } else {
                    lcsTable[i][j] = lcsTable[i][j - 1];
                    directionTable[i][j] = 'L';
                }
            }
        }

        int i = left.length();
        int j = top.length();
        char[] lcsString = new char[lcsTable[i][j]];
        int index = lcsString.length - 1;

        while (i > 0 && j > 0) {
            if (directionTable[i][j] == 'D') {
                lcsString[index--] = left.charAt(i - 1);
                i--;
                j--;
            } else if (directionTable[i][j] == 'U') {
                i--;
            } else if (directionTable[i][j] == 'L'){
                j--;
            }
        }

        return new String(lcsString);
    }
}
