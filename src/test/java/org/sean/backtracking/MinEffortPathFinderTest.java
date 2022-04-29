package org.sean.backtracking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinEffortPathFinderTest {

    private MinEffortPathFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new MinEffortPathFinder();
    }

    @Test
    public void minimumEffortPath() {

        int result = finder.minimumEffortPath(new int[][]{
                        {1, 2, 2},
                        {3, 8, 2},
                        {5, 3, 5},
                }
        );

        assertEquals(2, result);
    }
}