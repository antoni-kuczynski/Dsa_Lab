package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

public class MatrixChainOrderTopDown extends MatrixChainOrder {

    @Override
    public MatrixChainOrderResult findOptimalOrder(int[] matrixSizes) {
        validateInput(matrixSizes);

        int n = matrixSizes.length - 1;
        int[][] mem = new int[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n+1; j++) {
                mem[i][j] = -1;
            }
        }

        int minCost = lookupChain(matrixSizes, 1, n, mem);
        return new MatrixChainOrderResult(minCost);

    }

    private int lookupChain(int[] matrixSizes, int i, int j, int[][] memory) {
        if (memory[i][j] != -1) {
            return memory[i][j];
        }

        if (i == j) {
            memory[i][j] = 0;
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int costLeft = lookupChain(matrixSizes, i, k, memory);
            int costRight = lookupChain(matrixSizes, k + 1, j, memory);
            int costMultiply = matrixSizes[i - 1] * matrixSizes[k] * matrixSizes[j];
            int q = costLeft + costRight + costMultiply;
            if (q < min) {
                min = q;
            }

        }
        memory[i][j] = min;

        return memory[i][j];
    }

}
