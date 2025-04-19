package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodSubarrayCounterTest {

    private GoodSubarrayCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new GoodSubarrayCounter();
    }

    @Test
    public void countGood() {
        long ans = counter.countGood(new int[]{3, 1, 4, 3, 2, 2, 4, 3, 1, 4, 3, 2}, 2);
        assertEquals(31, ans);
    }
}