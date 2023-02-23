package org.sean.greedy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPOTest {

    private IPO ipo;

    @Before
    public void setUp() throws Exception {
        ipo = new IPO();
    }

    @Test
    public void findMaximizedCapital() {
        assertEquals(4, ipo.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));

        assertEquals(6, ipo.findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
    }
}