package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZeroMatrixTest {

    private ZeroMatrix zeroMatrix;

    @Before
    public void setUp() throws Exception {
        int[][] mtx = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 0, 8},
                {9, 0, 11, 12},
                {13, 14, 15, 16}
        };
        zeroMatrix = new ZeroMatrix(mtx);
    }

    @Test
    public void setZero() {
        int[][] mtxResult = new int[][]{
                {1, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {13, 0, 0, 16}
        };
        zeroMatrix.setZero();
        assertArrayEquals(mtxResult, zeroMatrix.getMatrix());
    }
}