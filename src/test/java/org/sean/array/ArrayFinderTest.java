package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayFinderTest {

    private ArrayFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new ArrayFinder();

    }

    @Test
    public void findOriginalArray() {
        int[] array = finder.findOriginalArray(new int[]{1, 3, 4, 2, 6, 8});
        assertArrayEquals(new int[]{1, 3, 4}, array);
    }

    @Test
    public void findInvalidArray() {
        assertArrayEquals(new int[0], finder.findOriginalArray(new int[]{6, 3, 0, 1}));
    }
}