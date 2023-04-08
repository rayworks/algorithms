package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoatCounterTest {

    private BoatCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new BoatCounter();
    }

    @Test
    public void numRescueBoats() {
        assertEquals(2, counter.numRescueBoats(new int[]{5, 1, 4, 2}, 6));
    }
}