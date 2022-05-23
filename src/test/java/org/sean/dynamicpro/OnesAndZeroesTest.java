package org.sean.dynamicpro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OnesAndZeroesTest {

    private OnesAndZeroes obj;

    @Before
    public void setUp() throws Exception {
        obj = new OnesAndZeroes();
    }

    @Test
    public void findMaxForm() {
        int maxForm = obj.findMaxForm(new String[]{
                        "11", "11", "0", "0", "10", "1", "1", "0", "11", "1", "0", "111", "11111000", "0", "11", "000", "1", "1", "0", "00", "1", "101", "001", "000", "0", "00", "0011", "0", "10000"},
                90,
                66
        );
        assertEquals(29, maxForm);
    }
}