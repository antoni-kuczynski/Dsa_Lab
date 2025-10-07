package pl.edu.pw.ee.aisd_ex_0.searcher.binary_search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd_ex_0.searcher.linear_search.LinearSearch;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest {
    private BinarySearch searcher;

    @BeforeEach
    public void setUp() {
        searcher = new BinarySearch();
    }

    @Test
    public void shouldThrowExceptionWhenNumsArrayIsNull() {
        //given
        int[] nums = null;
        int toFind = 10;

        //when
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> searcher.search(nums, toFind)
        );
        String actualMessage = ex.getMessage();

        //then
        String expectedMessage = "The nums array is null.";
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void shouldThrowExceptionWhenArrayIsNotSorted() {
        //given
        int[] nums = {8,4,1,5,2,3};
        int toFind = 1;

        //when
        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> searcher.search(nums, toFind)
        );
        String actualMessage = ex.getMessage();

        //then
        String expectedMessage = "The nums array is not sorted in ascending order.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testIfElementIsInTheMiddle() {
        //given
        int[] nums = {1,2,3,4,5,6};
        int toFind = 3;

        //when
        int actualIndex = searcher.search(nums, toFind);

        //then
        int expectedIndex = 2;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testBinarySearch_ElementAtBeginning() {
        //given
        int[] nums = {1,2,3,4,5,6};
        int toFind = 1;

        //when
        int actualIndex = searcher.search(nums, toFind);

        //then
        int expectedIndex = 0;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testBinarySearch_ElementAtTheEnd() {
        //given
        int[] nums = {1,2,3,4,5,6};
        int toFind = 6;

        //when
        int actualIndex = searcher.search(nums, toFind);

        //then
        int expectedIndex = 5;
        assertEquals(expectedIndex, actualIndex);
    }
}