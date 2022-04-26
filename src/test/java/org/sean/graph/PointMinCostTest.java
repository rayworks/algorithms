package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointMinCostTest {

    private PointMinCost minCost;

    @Before
    public void setUp() throws Exception {
        minCost = new PointMinCost();
    }

    @Test
    public void minCostConnectPoints() {
        int actual =
                minCost.minCostConnectPoints(
                        new int[][] {
                            {11, -6}, {9, -19}, {16, -13}, {4, -9}, {20, 4}, {20, 7}, {-9, 18},
                            {10, -15}, {-15, 3}, {6, 6}
                        });
        assertEquals(113, actual);
    }
}
