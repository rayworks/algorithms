package org.sean.greedy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircularArrayTest {

    private CircularArray array;

    @Before
    public void setUp() throws Exception {
        array = new CircularArray();
    }

    @Test
    public void minMoves() {
        assertEquals(12, array.minMoves(new int[]{3, 0, 2, 1, -7, 2, 1}));

        assertEquals(17, array.minMoves(new int[]{2, 13, -15, 0}));
    }
}