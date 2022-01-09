package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PivotFinderTest {

    private PivotFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new PivotFinder();
    }

    @Test
    public void pivotIndex() {
        assertEquals(3, finder.pivotIndex(new int[] {1, 7, 3, 6, 5, 6}));

        assertEquals(-1, finder.pivotIndex(new int[] {1, 2, 3}));

        assertEquals(0, finder.pivotIndex(new int[] {2, 1, -1}));
    }
}
