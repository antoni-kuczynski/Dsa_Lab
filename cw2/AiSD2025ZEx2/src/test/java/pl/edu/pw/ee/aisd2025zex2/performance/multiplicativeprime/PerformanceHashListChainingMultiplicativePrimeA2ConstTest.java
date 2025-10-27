package pl.edu.pw.ee.aisd2025zex2.performance.multiplicativeprime;

import pl.edu.pw.ee.aisd2025zex2.multiplicative.HashListChainingMultiplicativeHashingA2;
import pl.edu.pw.ee.aisd2025zex2.performance.PerformanceTest;

import static pl.edu.pw.ee.aisd2025zex2.performance.utils.HashSizeGenerator.generateHashSizePrimeNums;

public class PerformanceHashListChainingMultiplicativePrimeA2ConstTest extends PerformanceTest {

    public PerformanceHashListChainingMultiplicativePrimeA2ConstTest() {
        super(HashListChainingMultiplicativeHashingA2.class);
    }

    @Override
    public int[] getAllHashSizes() {
        return generateHashSizePrimeNums(N_VARIANTS);
    }
}
