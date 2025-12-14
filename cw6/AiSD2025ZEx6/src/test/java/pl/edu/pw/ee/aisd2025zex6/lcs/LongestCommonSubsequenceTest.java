package pl.edu.pw.ee.aisd2025zex6.lcs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongestCommonSubsequenceTest {
    private LongestCommonSubsequence lcs;

    @BeforeEach
    public void setUp() {
        lcs = new LongestCommonSubsequence();
    }

    @Test
    public void shouldWork_ForBasicWordCombination() {
        //given
        String left = "kratka";
        String top = "kaczka";

        //when
        String s = lcs.findLcs(left, top);

        //then
        String expected = "kaka";
        assertEquals(expected, s);
    }

    @Test
    public void shouldWork_ForReversedWordCombination() {
        //given
        String left = "kaczka";
        String top = "kratka";

        //when
        String s = lcs.findLcs(left, top);

        //then
        String expected = "kaka";
        assertEquals(expected, s);
    }

    @Test
    public void shouldWork_ForRepeatedWords() {
        //given
        String left = "kaczka";
        String top = "kaczka";

        //when
        String s = lcs.findLcs(left, top);

        //then
        String expected = "kaczka";
        assertEquals(expected, s);
    }

    @Test
    public void shouldWork_ForCompletelyDifferentWords() {
        //given
        String left = "abc";
        String top = "efg";

        //when
        String s = lcs.findLcs(left, top);

        //then
        String expected = "";
        assertEquals(expected, s);
    }

    @Test
    public void shouldWork_IfOneWordIsEmpty() {
        //given
        String left = "";
        String top = "kratka";

        //when
        String s = lcs.findLcs(left, top);

        //then
        String expected = "";
        assertEquals(expected, s);
    }

    @Test
    public void shouldNotThrow_WhenWordIsNull() {
        //given
        String left = null;
        String top = "kratka";

        //when
        assertThrows(IllegalArgumentException.class, () -> lcs.findLcs(left, top), "Params cannot be null!");
    }

}