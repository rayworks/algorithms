package org.sean.array;

import junit.framework.TestCase;

import static org.junit.Assert.assertArrayEquals;

public class IntervalMergerTest extends TestCase {

    private IntervalMerger merger;

    public void setUp() throws Exception {
        merger = new IntervalMerger();
    }

    public void testMerge() {
        int[][] intervals = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
        assertArrayEquals(new int[][]{{1, 10}}, merger.merge(intervals));

        intervals = new int[][]{{1, 4}, {4, 5}};
        assertArrayEquals(new int[][]{{1, 5}}, merger.merge(intervals));

        intervals = new int[][]{
                {3, 3}, {1, 1}, {0, 2}, {2, 2}, {1, 2}, {1, 3}, {1, 1}, {3, 3}, {2, 3}, {4, 6}
        };
        assertArrayEquals(new int[][]{{0, 3}, {4, 6}}, merger.merge(intervals));
    }
}