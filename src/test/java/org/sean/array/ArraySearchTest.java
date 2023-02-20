package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArraySearchTest {

    private ArraySearch finder;

    @Before
    public void setUp() throws Exception {
        finder = new ArraySearch();
    }

    @Test
    public void search() {
        assertEquals(-1, finder.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }
}