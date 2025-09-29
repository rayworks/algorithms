package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubArrayMinDifferTest {

    private SubArrayMinDiffer minDiffer;

    @Before
    public void setUp() throws Exception {
        minDiffer = new SubArrayMinDiffer();
    }

    @Test
    public void splitArray() {
        assertEquals(5L, minDiffer.splitArray(new int[]{
                1, 2, 3, 5, 4, 2
        }));
    }

    @Test
    public void splitInvalidArray() {
        assertEquals(-1L, minDiffer.splitArray(new int[]{3, 1, 2}));
    }
}