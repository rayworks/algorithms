package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class MultiSrcFloodFillTest {

    private MultiSrcFloodFill floodFill;

    @Before
    public void setUp() throws Exception {
        floodFill = new MultiSrcFloodFill();
    }

    @Test
    public void colorGrid() {
        int[][] res = floodFill.colorGrid(3, 3, new int[][]{
                {0, 1, 3}, {1, 1, 5}
        });

        assertArrayEquals(new int[][]{
                {3, 3, 3}, {5, 5, 5}, {5, 5, 5}
        }, res);
    }
}