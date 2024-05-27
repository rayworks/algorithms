package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumInSpecialArrayTest {

    private NumInSpecialArray obj;

    @Before
    public void setUp() throws Exception {
        obj = new NumInSpecialArray();
    }

    @Test
    public void specialArray() {
        assertEquals(3, obj.specialArray(new int[]{0, 4, 3, 0, 4}));
    }
}