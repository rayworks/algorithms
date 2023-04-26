package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntArrayTest {

    private IntArray array;

    @Before
    public void setUp() throws Exception {
        array = new IntArray();
    }

    @Test
    public void numberOfArrays() {
        Assert.assertEquals(8, array.numberOfArrays("1317", 2000));
    }
}