package org.sean.sorting;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinMoveCalculatorTest {

    private MinMoveCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new MinMoveCalculator();
    }

    @Test
    public void minMoves2() {
        assertEquals(14, calculator.minMoves2(new int[]{1, 0, 0, 8, 6}));
    }
}