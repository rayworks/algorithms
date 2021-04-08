package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WaterBarTest {

    private WaterBar waterBar;

    @Before
    public void setUp() throws Exception {
        waterBar = new WaterBar();
    }

    @Test
    public void testTrapWater() {
        int size = waterBar.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        Assert.assertEquals(6, size);

        size = waterBar.trap(new int[]{4,2,0,3,2,5});
        Assert.assertEquals(9, size);
    }
}