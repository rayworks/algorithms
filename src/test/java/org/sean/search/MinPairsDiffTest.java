package org.sean.search;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinPairsDiffTest {

    private MinPairsDiff diff;

    @Before
    public void setUp() throws Exception {
        diff = new MinPairsDiff();
    }

    @Test
    public void minimizeMax() {
        assertEquals(1, diff.minimizeMax(new int[]{10, 1, 2, 7, 1, 3}, 2));
    }
}