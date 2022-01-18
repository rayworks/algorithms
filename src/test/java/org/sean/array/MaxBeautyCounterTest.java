package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MaxBeautyCounterTest {

    private MaxBeautyCounter beautyCounter;

    @Before
    public void setup() {
        beautyCounter = new MaxBeautyCounter();
    }

    @Test
    public void testMaximumBeauty() {
        int[] values = beautyCounter.maximumBeauty(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}},
                new int[]{1, 2, 3, 4, 5, 6});
        assertArrayEquals(new int[]{2, 4, 5, 5, 6, 6}, values);


        values = beautyCounter.maximumBeauty(new int[][]{{1,2},{1,2},{1,3},{1,4}}, new int[]{1});
        assertArrayEquals(new int[]{4}, values);
    }
}