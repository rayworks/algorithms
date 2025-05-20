package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinSwapsCounterTest {

    private MinSwapsCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new MinSwapsCounter();
    }

    @Test
    public void minSwaps() {
        int cnt = counter.minSwaps(new int[]{18, 43, 34, 16});
        assertEquals(2, cnt);
    }
}