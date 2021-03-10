package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MissingPositiveFinderTest {

    private MissingPositiveFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new MissingPositiveFinder();
    }

    @Test
    public void firstMissingPositive() {
        assertEquals(finder.firstMissingPositive(new int[] {3, 4, -1, 1}), 2);
        assertEquals(finder.firstMissingPositive(new int[] {1, 2, 0}), 3);
        assertEquals(finder.firstMissingPositive(new int[] {1, 1}), 2);
        assertEquals(finder.firstMissingPositive(new int[] {7,8,9,11,12}), 1);
    }
}
