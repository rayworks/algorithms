package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PartitionArrayTest {

    private PartitionArray array;

    @Before
    public void setUp() throws Exception {
        array = new PartitionArray();
    }

    @Test
    public void maxSumAfterPartitioning() {
        Assert.assertEquals(83,
                array.maxSumAfterPartitioning(new int[]{1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3}, 4));
    }
}