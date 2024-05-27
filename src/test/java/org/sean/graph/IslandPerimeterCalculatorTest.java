package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IslandPerimeterCalculatorTest {

    private IslandPerimeterCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new IslandPerimeterCalculator();
    }

    @Test
    public void islandPerimeter() {
        int perimeter = calculator.islandPerimeter(new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        });
        assertEquals(16, perimeter);
    }
}