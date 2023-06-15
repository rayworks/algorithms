package org.sean.search;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoundedArrayTest {

    private BoundedArray array;

    @Before
    public void setUp() throws Exception {
        array = new BoundedArray();
    }

    @Test
    public void maxValue() {
        assertEquals(2, array.maxValue(4, 2, 6));
        assertEquals(3, array.maxValue(6, 1, 10));
    }
}