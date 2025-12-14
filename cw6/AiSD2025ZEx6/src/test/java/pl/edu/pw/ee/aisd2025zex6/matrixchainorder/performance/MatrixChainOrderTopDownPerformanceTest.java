package pl.edu.pw.ee.aisd2025zex6.matrixchainorder.performance;

import pl.edu.pw.ee.aisd2025zex6.matrixchainorder.MatrixChainOrderRecursive;
import pl.edu.pw.ee.aisd2025zex6.matrixchainorder.MatrixChainOrderTopDown;

public class MatrixChainOrderTopDownPerformanceTest extends MatrixChainOrderPerformanceTest {

    public MatrixChainOrderTopDownPerformanceTest() {
        super(new MatrixChainOrderTopDown(), 100);
    }
}
