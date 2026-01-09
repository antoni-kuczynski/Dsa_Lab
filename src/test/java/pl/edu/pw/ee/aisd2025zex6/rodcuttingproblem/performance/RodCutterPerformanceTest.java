package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem.performance;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem.RodCutter;

import java.util.Random;

public abstract class RodCutterPerformanceTest {
    private int SUBPROBLEM_MAX = 100_000_000;
    private final Random rand = new Random(11111);
    private final RodCutter rodCutter;
    private int step;

    public RodCutterPerformanceTest(RodCutter cutter, int step) {
        this.rodCutter = cutter;
        this.step = step;
    }

    @Test
    public void measureTimeForSubProblems_AndBreakIfLargerThan4Seconds() {
        System.out.println("Size\tTime(ns)");
        for (int i = 0; i <= SUBPROBLEM_MAX; i += step) {
            int[] prices = new int[i];
            int rodLength = i;
            for (int it = 0; it < i; it++) {
                prices[it] = rand.nextInt(5, 50);
            }

            long prevTime = System.nanoTime();
            rodCutter.cutRod(prices, rodLength);
            long dTime = System.nanoTime() - prevTime;
            System.out.println(i + "\t" + dTime);
            if (dTime > 4L * 1000 * 1000 * 1000) {
                System.out.println("Max subproblem size for " + rodCutter.getClass().getSimpleName() + " (< 4s): " + (i - 1));
                break;
            }


        }

    }


}
