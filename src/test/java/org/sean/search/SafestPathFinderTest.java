package org.sean.search;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SafestPathFinderTest {

    private SafestPathFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new SafestPathFinder();
    }

    @Test
    public void maximumSafenessFactor() {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(0, 0, 0, 1));
        grid.add(Arrays.asList(0, 0, 0, 0));
        grid.add(Arrays.asList(0, 0, 0, 0));
        grid.add(Arrays.asList(1, 0, 0, 0));

        assertEquals(2, finder.maximumSafenessFactor(grid));
    }
}