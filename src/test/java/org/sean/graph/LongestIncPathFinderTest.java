package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestIncPathFinderTest {

    private LongestIncPathFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new LongestIncPathFinder();
    }

    @Test
    public void longestIncreasingPath() {
        int len = finder.longestIncreasingPath(new int[][]{
                {9, 9, 4}, {6, 6, 8}, {2, 1, 1}
        });
        assertEquals(4, len);
    }
}