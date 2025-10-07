package pl.edu.pw.ee.aisd_ex_0.extratasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MountainPeakProblemTest {
    private MountainPeakProblem problem;

    @BeforeEach
    void setUp() {
        problem = new MountainPeakProblem();
    }

    @Test
    public void testFor3ElementArray() {
        //given
        int[] arr = {2,5,2};

        //when
        int actualIndex = problem.peakIndexInMountainArray(arr);

        //then
        int expectedIndex = 1;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testFor4ElementArray() {
        //given
        int[] arr = {2,3,5,2};

        //when
        int actualIndex = problem.peakIndexInMountainArray(arr);

        //then
        int expectedIndex = 2;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testForLongArray() {
        //given
        int[] arr = {1,2,3,4,5,6,9,142,356,8,6,3,1,0};

        //when
        int actualIndex = problem.peakIndexInMountainArray(arr);

        //then
        int expectedIndex = 8;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testForPeakOnTheLeft() {
        //given
        int[] arr = {9,8,7,6,5,4};

        //when
        int actualIndex = problem.peakIndexInMountainArray(arr);

        //then
        int expectedIndex = 0;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testForPeakOnTheRight() {
        //given
        int[] arr = {1,2,3,4,5,6};

        //when
        int actualIndex = problem.peakIndexInMountainArray(arr);

        //then
        int expectedIndex = 5;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void shouldThrowExceptionWhenNumsArrayIsNull() {
        //given
        int[] arr = null;

        //when
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> problem.peakIndexInMountainArray(arr)
        );
        String actualMessage = ex.getMessage();

        //then
        String expectedMessage = "The nums array is null.";
        assertEquals(expectedMessage,actualMessage);
    }
}