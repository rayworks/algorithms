package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxMovesCounterTest {

    private MaxMovesCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new MaxMovesCounter();
    }

    @Test
    public void maxMoves() {
        int maxMoves = counter.maxMoves(new int[][]{
                {2, 4, 3, 5},
                {5, 4, 9, 3},
                {3, 4, 2, 11},
                {10, 9, 13, 15}
        });
        Assert.assertEquals(3, maxMoves);
    }
}