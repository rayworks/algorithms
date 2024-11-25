package org.sean.search;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SlidingPuzzleTest {

    private SlidingPuzzle puzzle;

    @Before
    public void setUp() throws Exception {
        puzzle = new SlidingPuzzle();
    }

    @Test
    public void slidingPuzzle() {
        int res = puzzle.slidingPuzzle(new int[][]{
                {4, 1, 2},
                {5, 0, 3}
        });
        assertEquals(5, res);
    }
}