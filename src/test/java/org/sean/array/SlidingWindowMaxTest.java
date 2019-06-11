package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlidingWindowMaxTest {

    private SlidingWindowMax slidingWindowMax;

    @Before
    public void setUp() throws Exception {
        slidingWindowMax = new SlidingWindowMax();
    }

    @Test
    public void maxSlidingWindow() {
        int[] ints = slidingWindowMax.maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3);
        Assert.assertArrayEquals(new int[] {3, 3, 5, 5, 6, 7}, ints);
    }
}
