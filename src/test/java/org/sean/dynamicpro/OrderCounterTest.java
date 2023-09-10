package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderCounterTest {

    private OrderCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new OrderCounter();
    }

    @Test
    public void countOrders() {
        Assert.assertEquals(90, counter.countOrders(3));
    }
}