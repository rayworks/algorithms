package org.sean.recursive;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IslandMaxAreaTest {

    private IslandMaxArea maxArea;

    @Before
    public void setUp() throws Exception {
        maxArea = new IslandMaxArea();
    }

    @Test
    public void maxAreaOfIsland() {
        int max =
                maxArea.maxAreaOfIsland(
                        new int[][] {
                            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
                        });
        assertEquals(6, max);
    }
}
