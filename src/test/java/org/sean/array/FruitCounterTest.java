package org.sean.array;

import junit.framework.TestCase;

public class FruitCounterTest extends TestCase {

    public void testTotalFruit() {
        FruitCounter counter = new FruitCounter();

        assertEquals(3, counter.totalFruit(new int[]{1, 2, 1}));
        assertEquals(3, counter.totalFruit(new int[]{0, 1, 2, 2}));
        assertEquals(4, counter.totalFruit(new int[]{1, 2, 3, 2, 2}));
        assertEquals(5, counter.totalFruit(new int[]{0, 1, 6, 6, 4, 4, 6}));
    }
}