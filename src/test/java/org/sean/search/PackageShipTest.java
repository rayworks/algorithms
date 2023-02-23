package org.sean.search;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackageShipTest {
    private PackageShip ship;

    @Before
    public void setUp() throws Exception {
        ship = new PackageShip();
    }

    @Test
    public void shipWithinDays() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int max = ship.shipWithinDays(arr, 3);
        assertEquals(170, max);
    }
}