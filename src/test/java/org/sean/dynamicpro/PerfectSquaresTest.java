package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PerfectSquaresTest {

    public PerfectSquares obj;

    @Before
    public void setUp() throws Exception {
        obj = new PerfectSquares();
    }

    @Test
    public void numSquares() {
        Assert.assertEquals(3, obj.numSquares(12));
    }
}