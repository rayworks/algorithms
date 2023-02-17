package org.sean.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FuelCostTest {

    private FuelCost calc;

    @Before
    public void setUp() throws Exception {
        calc = new FuelCost();
    }

    @Test
    public void minimumFuelCost() {
        long cost = calc.minimumFuelCost(new int[][]
                {{0, 1}, {2, 1}, {3, 2}, {4, 2}, {4, 5}, {6, 0}, {5, 7}, {8, 4}, {9, 2}}, 2);
        
        assertEquals(16, cost);
    }
}