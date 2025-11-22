package pl.edu.pw.ee.aisd2025zex4.performance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class DataGeneratorTest {
    private DataGenerator gen;

    @BeforeEach
    void setUp() {
        gen = new DataGenerator();
    }

    private static boolean containsNonAsciiString(String[] s) {
        boolean b = true;
        for (String s1 : s) {
            if (!StandardCharsets.US_ASCII.newEncoder().canEncode(s1)) { //checks if only contains ASCII chars
                b = false;
                break;
            }
        }
        return b;
    }

    private static boolean isSorted(String[] s) {
        for (int i = 1; i < s.length; i++) {
            String s1 = s[i - 1];
            String s2 = s[i];

            if (s2.compareTo(s1) < 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isReverseSorted(String[] s) {
        for (int i = 1; i < s.length; i++) {
            String s1 = s[i - 1];
            String s2 = s[i];

            if (s2.compareTo(s1) > 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void shouldGenerate_Correct_RandomData() {
        //given
        String[] s = gen.generateRandData(100);

        //when
        boolean b = containsNonAsciiString(s);

        //then
        assertEquals(100, s.length, "Array length is not 100");
        assertTrue(b, "Array contains a non ascii string");
    }

    @Test
    public void shouldGenerate_Correct_AscendingData() {
        //given
        String[] s = gen.generateAscData(100);

        //when
        boolean b = containsNonAsciiString(s);
        boolean isSorted = isSorted(s);

        //then
        assertEquals(100, s.length, "Array length is not 100");
        assertTrue(b, "Array contains a non ascii string");
        assertTrue(isSorted, "Array is not sorted");
    }

    @Test
    public void shouldGenerate_Correct_DescendingData() {
        //given
        String[] s = gen.generateDescData(100);

        //when
        boolean b = containsNonAsciiString(s);
        boolean isSorted = isReverseSorted(s);

        //then
        assertEquals(100, s.length, "Array length is not 100");
        assertTrue(b, "Array contains a non ascii string");
        assertTrue(isSorted, "Array is not reverse sorted");
    }

}