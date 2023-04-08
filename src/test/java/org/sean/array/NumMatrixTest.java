package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumMatrixTest {

    private NumMatrix matrix;

    @Before
    public void setUp() throws Exception {
        matrix = new NumMatrix(new int[][]{
                new int[]{3, 0, 1, 4, 2},
                new int[]{5, 6, 3, 2, 1},
                new int[]{1, 2, 0, 1, 5},
                new int[]{4, 1, 0, 1, 7},
                new int[]{1, 0, 3, 0, 5}
        });
    }

    @Test
    public void sumRegion() {
        assertEquals(8, matrix.sumRegion(2, 1, 4, 3));
        assertEquals(11, matrix.sumRegion(1, 1, 2, 2));
        assertEquals(12, matrix.sumRegion(1, 2, 2, 4));
    }
}