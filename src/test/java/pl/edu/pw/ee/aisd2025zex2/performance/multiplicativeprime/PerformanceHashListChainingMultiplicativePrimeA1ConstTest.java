package pl.edu.pw.ee.aisd2025zex2.performance.multiplicativeprime;

import pl.edu.pw.ee.aisd2025zex2.HashListChainingMultiplicative;
import pl.edu.pw.ee.aisd2025zex2.performance.PerformanceTest;

import static pl.edu.pw.ee.aisd2025zex2.performance.utils.HashSizeGenerator.generateHashSizePrimeNums;

public class PerformanceHashListChainingMultiplicativePrimeA1ConstTest extends PerformanceTest {

    public PerformanceHashListChainingMultiplicativePrimeA1ConstTest() {
        super(HashListChainingMultiplicative.class);
    }

    @Override
    public int[] getAllHashSizes() {
        return generateHashSizePrimeNums(N_VARIANTS);
    }
}
