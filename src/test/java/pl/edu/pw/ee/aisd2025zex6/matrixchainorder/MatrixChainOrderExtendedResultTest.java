package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatrixChainOrderExtendedResultTest {

    @Test
    public void shouldThrowException_WhenSolutionIsNull() {
        //given
        int[][] solutions = null;

        //when
        MatrixChainOrderExtendedResult result =
                new MatrixChainOrderExtendedResult(0, solutions);

        //then
        assertThrows(IllegalArgumentException.class, result::reconstructOptimalSolutions, "Solution array can't be null / be empty!");
    }

    @Test
    public void shouldThrowException_WhenInvalidSplitOccured() {
        //given
        int[][] solutions = new int[4][4];
        solutions[1][3] = 1; //A1..A3
        solutions[1][2] = 1; //A1..A2
        solutions[2][3] = 1; //A2..A3

        //when
        MatrixChainOrderExtendedResult result =
                new MatrixChainOrderExtendedResult(0, solutions);
        assertThrows(IllegalArgumentException.class, result::reconstructOptimalSolutions);
    }

    @Test
    public void shouldReturnSingleMatrix_WhenOnlyOneMatrix2() {
        //given
        int[][] solutions = new int[4][4];
        solutions[1][3] = 1;
        solutions[2][3] = 2;

        //when
        MatrixChainOrderExtendedResult result =
                new MatrixChainOrderExtendedResult(0, solutions);
        String reconstruction = result.reconstructOptimalSolutions();

        //then
        assertEquals("(A1 x (A2 x A3))", reconstruction);
    }

    @Test
    public void shouldReturnSingleMatrix_WhenOnlyOneMatrix() {
        //given
        int[][] solutions = new int[2][2];

        //when
        MatrixChainOrderExtendedResult result =
                new MatrixChainOrderExtendedResult(0, solutions);
        String reconstruction = result.reconstructOptimalSolutions();

        //then
        assertEquals("A1", reconstruction);
    }

    @Test
    void shouldReturnParenthesizedMultiplication_WhenTwoMatrices() {
        //given
        int[][] solutions = new int[3][3];
        solutions[1][2] = 1; //A1..A2

        //when
        MatrixChainOrderExtendedResult result =
                new MatrixChainOrderExtendedResult(0, solutions);

        String reconstruction = result.reconstructOptimalSolutions();

        //then
        assertEquals("(A1 x A2)", reconstruction);
    }

    @Test
    void shouldReturnNestedParentheses_WhenThreeMatrices() {
        //given
        int[][] solutions = new int[4][4];
        solutions[1][3] = 1; //A1..A3
        solutions[1][2] = 1; //A1..A2
        solutions[2][3] = 2; //A2..A3

        //when
        MatrixChainOrderExtendedResult result =
                new MatrixChainOrderExtendedResult(0, solutions);

        String reconstruction = result.reconstructOptimalSolutions();

        //then
        assertEquals("(A1 x (A2 x A3))", reconstruction);
    }

    @Test
    void shouldReturnComplexParentheses_WhenFourMatrices() {
        //given
        int[][] solutions = new int[5][5];
        solutions[1][4] = 2;
        solutions[1][2] = 1;
        solutions[3][4] = 3;

        //when
        MatrixChainOrderExtendedResult result =
                new MatrixChainOrderExtendedResult(0, solutions);

        String reconstruction = result.reconstructOptimalSolutions();

        //then
        assertEquals("((A1 x A2) x (A3 x A4))", reconstruction);
    }

}