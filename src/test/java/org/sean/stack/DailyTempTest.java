package org.sean.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DailyTempTest {

    private DailyTemp temp;

    @Before
    public void setUp() throws Exception {
        temp = new DailyTemp();
    }

    @Test
    public void dailyTemperatures() {
        int[] out = temp.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        Assert.assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, out);
    }
}