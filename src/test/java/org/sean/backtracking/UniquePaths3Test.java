package org.sean.backtracking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniquePaths3Test {

    private UniquePaths3 paths;

    @Before
    public void setUp() throws Exception {
        paths = new UniquePaths3();
    }

    @Test
    public void uniquePathsIII() {
        int count = paths.uniquePathsIII(new int[][]{
                {1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}
        });
        assertEquals(2, count);
    }
}