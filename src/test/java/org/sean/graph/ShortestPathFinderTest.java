package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShortestPathFinderTest {

    private ShortestPathFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new ShortestPathFinder();
    }

    @Test
    public void shortestPathLength() {
        int len = finder.shortestPathLength(new int[][]{
                {1},
                {0, 2, 4},
                {1, 3, 4},
                {2},
                {1, 2}
        });
        assertEquals(4, len);
    }
}