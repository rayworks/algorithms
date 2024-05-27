package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LargeIslandTest {

    private LargeIsland obj;

    @Before
    public void setUp() throws Exception {
        obj = new LargeIsland();
    }

    @Test
    public void largestIsland() {
        assertEquals(18, obj.largestIsland(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0}
        }));

    }
}