package pl.edu.pw.ee.aisd_ex_0.searcher.linear_search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinearSearchTest {
   private LinearSearch searcher;

   @BeforeEach
   public void setUp() {
       searcher = new LinearSearch();
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
    public void testOneElementArray() {
        //given
        int[] nums = {1};
        int toFind = 1;

        //when
        int foundIndex = searcher.search(nums, toFind);

        //then
        int expectedIndex = 0;
        assertEquals(expectedIndex, foundIndex);
    }

    @Test
    public void testIfElementIsInTheMiddle() {
        //given
        int[] nums = {7,5,4,1,2,9};
        int toFind = 1;

        //when
        int actualIndex = searcher.search(nums, toFind);

        //then
        int expectedIndex = 3;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testBinarySearch_ElementAtBeginning() {
        //given
        int[] nums = {8,5,4,23,1,2};
        int toFind = 8;

        //when
        int actualIndex = searcher.search(nums, toFind);

        //then
        int expectedIndex = 0;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testBinarySearch_ElementAtTheEnd() {
        //given
        int[] nums = {5,4,2,1,35,6};
        int toFind = 6;

        //when
        int actualIndex = searcher.search(nums, toFind);

        //then
        int expectedIndex = 5;
        assertEquals(expectedIndex, actualIndex);
    }
}
