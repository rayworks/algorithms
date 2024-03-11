package org.sean.dynamicpro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxRectangleTest {

    private MaxRectangle rect;

    @Before
    public void setUp() throws Exception {
        rect = new MaxRectangle();
    }

    @Test
    public void maximalRectangle() {
        int res = rect.maximalRectangle(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        });
        assertEquals(6, res);
    }
}