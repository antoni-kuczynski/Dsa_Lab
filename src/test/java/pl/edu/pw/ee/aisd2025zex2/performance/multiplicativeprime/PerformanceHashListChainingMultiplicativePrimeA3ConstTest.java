package pl.edu.pw.ee.aisd2025zex2.performance.multiplicativeprime;

import pl.edu.pw.ee.aisd2025zex2.multiplicative.HashListChainingMultiplicativeHashingA3;
import pl.edu.pw.ee.aisd2025zex2.performance.PerformanceTest;

import static pl.edu.pw.ee.aisd2025zex2.performance.utils.HashSizeGenerator.generateHashSizePrimeNums;

public class PerformanceHashListChainingMultiplicativePrimeA3ConstTest extends PerformanceTest {

    public PerformanceHashListChainingMultiplicativePrimeA3ConstTest() {
        super(HashListChainingMultiplicativeHashingA3.class);
    }

    @Override
    public int[] getAllHashSizes() {
        return generateHashSizePrimeNums(N_VARIANTS);
    }
}
