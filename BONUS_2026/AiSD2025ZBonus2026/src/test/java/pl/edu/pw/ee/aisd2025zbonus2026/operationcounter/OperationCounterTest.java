package pl.edu.pw.ee.aisd2025zbonus2026.operationcounter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationCounterTest {
    private OperationCounter counter;

    @BeforeEach
    void setUp() {
        counter = new OperationCounter();
    }

    @Test
    public void should_ThrowExceptionForNegativeK() {
        int k = 0;

        assertThrows(IllegalArgumentException.class, () -> counter.countMinSumOfOperations(k), "k must be >= 1");
    }

    @Test
    public void should_CountProperly_ForKIntMaxValue() {
        int k = Integer.MAX_VALUE;

        int result = counter.countMinSumOfOperations(k);

        assertEquals(32, result);
    }

    @Test
    public void should_CountProperly_IfKFitsInTwoBits_AndEquals3() {
        int k = 3;

        int result = counter.countMinSumOfOperations(k);

        assertEquals(2, result);
    }

    @Test
    public void should_CountProperly_IfKFitsInTwoBits_AndEquals2() {
        int k = 2;

        int result = counter.countMinSumOfOperations(k);

        assertEquals(1, result);
    }

    @Test
    public void should_Return0_IfKIsOne() {
        int k = 1;

        int result = counter.countMinSumOfOperations(k);

        assertEquals(0, result);
    }

    @Test
    public void should_CountProperly_ForPowerOfTwo() {
        int power = 16;
        int k = 1 << power;

        int result = counter.countMinSumOfOperations(k);

        assertEquals(power, result);
    }

    @Test
    public void should_CountProperly_ForPowerOfTwoMinusOne() {
        int power = 16;
        int k = (1 << power) - 1;

        int result = counter.countMinSumOfOperations(k);

        assertEquals(power + 1, result);
    }

}