package pl.edu.pw.ee.aisd_ex_0.extratasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotOrColdProblemTest {
    private HotOrColdProblem problem;

    @BeforeEach
    public void init() {
        problem = new HotOrColdProblem();
    }

    @Test
    public void test_nEquals5() {
        //given
        int n = 5;

        //when
        int actualNumber = problem.guessNumber(n);

        //then
        int expectedGuess = 1;
        assertEquals(expectedGuess, actualNumber);
    }

    @Test
    public void test_nEquals7() {
        //given
        int n = 7;

        //when
        int actualNumber = problem.guessNumber(n);

        //then
        int expectedGuess = 4;
        assertEquals(expectedGuess, actualNumber);
    }

    @Test
    public void test_nEquals11() {
        //given
        int n = 11;

        //when
        int actualNumber = problem.guessNumber(n);

        //then
        int expectedGuess = 1;
        assertEquals(expectedGuess, actualNumber);
    }

    @Test
    public void test_nEquals8() {
        //given
        int n = 8;

        //when
        int actualNumber = problem.guessNumber(n);

        //then
        int expectedGuess = 4;
        assertEquals(expectedGuess, actualNumber);
    }

    @Test
    public void test_nEquals12() {
        //given
        int n = 12;

        //when
        int actualNumber = problem.guessNumber(n);

        //then
        int expectedGuess = 10;
        assertEquals(expectedGuess, actualNumber);
    }



    @Test
    public void test_nIsNegative() {
        //given
        int n = -1;

        //when
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> problem.guessNumber(n)
        );
        String actualMessage = ex.getMessage();

        //then
        String expectedMessage = "n cannot be negative";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void test_nIsIntMax() {
        //given
        int n = Integer.MAX_VALUE;

        //then
        assertDoesNotThrow(() -> problem.guessNumber(n));
    }
}