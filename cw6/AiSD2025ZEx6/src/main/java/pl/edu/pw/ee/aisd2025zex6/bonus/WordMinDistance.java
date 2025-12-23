package pl.edu.pw.ee.aisd2025zex6.bonus;

public class WordMinDistance {

    public int findMinDistance(String first, String second) {
        int n = first.length();
        int m = second.length();

        int[][] dp = new int[n + 1][m + 1];

        // Warunki początkowe
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        // Wypełnianie tablicy DP
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );
                }
            }
        }

        return dp[n][m];
    }
}
