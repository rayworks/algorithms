package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RightIntervalFinderTest {

    private RightIntervalFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new RightIntervalFinder();
    }

    @Test
    public void findRightInterval() {
        int[] arr = finder.findRightInterval(new int[][]{
                {4, 5},
                {2, 3},
                {1, 2}
        });
        assertArrayEquals(new int[]{-1, 0, 1}, arr);
    }
}