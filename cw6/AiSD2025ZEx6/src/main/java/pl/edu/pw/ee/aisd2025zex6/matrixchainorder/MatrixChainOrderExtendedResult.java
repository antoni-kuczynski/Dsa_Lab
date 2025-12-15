package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

public class MatrixChainOrderExtendedResult extends MatrixChainOrderResult {

    private final int[][] solutions;

    public MatrixChainOrderExtendedResult(int minMultiplyCost, int[][] solutions) {
        super(minMultiplyCost);
        this.solutions = solutions;
    }

    private void validateParams() {
        if (solutions == null || solutions.length == 0) {
            throw new IllegalArgumentException("Solution array can't be null / be empty!");
        }

        for (int[] i : solutions) {
            if (i == null) {
                throw new IllegalArgumentException("Solution array can't contain null subarrays!");
            }
        }
    }

    public String reconstructOptimalSolutions() {
        validateParams();
        return buildSolution(1, solutions.length - 1);
    }

    private String buildSolution(int i, int j) {
        if (i == j) {
            return "A" + i;
        }
        int k = solutions[i][j];
        return "(" + buildSolution(i, k) + " x " + buildSolution(k + 1, j) + ")";
    }


}
