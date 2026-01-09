package pl.edu.pw.ee.aisd2025zex2.performance;

import pl.edu.pw.ee.aisd2025zex2.HashListChainingMultiplicative;

import static pl.edu.pw.ee.aisd2025zex2.performance.utils.HashSizeGenerator.generateHashSizesPowerOf2;

public class PerformanceHashListChainingMultiplicativeHashingSizePowerOf2Test extends PerformanceTest {

    public PerformanceHashListChainingMultiplicativeHashingSizePowerOf2Test() {
        super(HashListChainingMultiplicative.class);
    }

    @Override
    public int[] getAllHashSizes() {
        return generateHashSizesPowerOf2(N_VARIANTS, INIT_SIZE);
    }
}
