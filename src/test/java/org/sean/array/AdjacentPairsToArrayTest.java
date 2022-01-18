package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacentPairsToArrayTest {

    private AdjacentPairsToArray pairsToArray;

    @Before
    public void setUp() throws Exception {
        pairsToArray = new AdjacentPairsToArray();
    }

    @Test
    public void restoreArray() {
        assertArrayEquals(new int[]{1, 2, 3, 4}, pairsToArray.restoreArray(new int[][]{{2, 1}, {3, 4}, {3, 2}}));
        assertArrayEquals(new int[]{-2, 4, 1, -3}, pairsToArray.restoreArray(new int[][]{{4, -2}, {1, 4}, {-3, 1}}));
        assertArrayEquals(new int[]{100000, -100000}, pairsToArray.restoreArray(new int[][]{{100000, -100000}}));
    }
}