package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IceCreamCounterTest {

    private IceCreamCounter iceCreamCounter;

    @Before
    public void setUp() throws Exception {
        iceCreamCounter = new IceCreamCounter();
    }

    @Test
    public void maxIceCream() {
        assertEquals(4, iceCreamCounter.maxIceCream(new int[]{1, 3, 2, 4, 1}, 7));

        assertEquals(0, iceCreamCounter.maxIceCream(new int[]{10, 6, 8, 7, 7, 8}, 5));

        assertEquals(6, iceCreamCounter.maxIceCream(new int[]{1, 6, 3, 1, 2, 5}, 20));
    }
}