package pl.edu.pw.ee.aisd2025zex6.matrixchainorder.performance;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex6.matrixchainorder.DataUtils;
import pl.edu.pw.ee.aisd2025zex6.matrixchainorder.MatrixChainOrder;
import pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem.RodCutter;

import java.util.Random;

public abstract class MatrixChainOrderPerformanceTest {
    private int SUBPROBLEM_MAX = 100_000;
    private final Random rand = new Random(11111);
    private final MatrixChainOrder chainOrder;
    private final int step;
    private final DataUtils dataUtils;

    public MatrixChainOrderPerformanceTest(MatrixChainOrder cutter, int step) {
        this.chainOrder = cutter;
        this.step = step;
        dataUtils = new DataUtils();
    }

    @Test
    public void measureTimeForSubProblems_AndBreakIfLargerThan4Seconds() {
        System.out.println("Size\tTime(ns)");
        for (int i = 2; i <= SUBPROBLEM_MAX; i += step) {
            int[] matrixesSizes = dataUtils.prepareRandomValidArray(i,5,50);
            for (int it = 0; it < i; it++) {
                matrixesSizes[it] = rand.nextInt(5, 50);
            }

            long prevTime = System.nanoTime();
            chainOrder.findOptimalOrder(matrixesSizes);
            long dTime = System.nanoTime() - prevTime;
            System.out.println(i + "\t" + dTime);
            if (dTime > 4L * 1000 * 1000 * 1000) {
                System.out.println("Max subproblem size for " + chainOrder.getClass().getSimpleName() + " (< 4s): " + (i - 1));
                break;
            }


        }

    }


}
