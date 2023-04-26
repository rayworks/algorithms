package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlidingSubarrayBeautyTest {

    private SlidingSubarrayBeauty subArray;

    @Before
    public void setUp() throws Exception {
        subArray = new SlidingSubarrayBeauty();
    }

    @Test
    public void getSubarrayBeauty() {
        int[] beauty = subArray.getSubarrayBeauty(new int[]{24, 45, -44, -33, 6, -40, -29, 2}, 4, 2);
        Assert.assertArrayEquals(new int[]{-33, -33, -40, -33, -29}, beauty);
    }
}