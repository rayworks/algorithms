package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IslandCounterTest {

    private IslandCounter counter;

    @Before
    public void setup() {
        counter = new IslandCounter();
    }

    @Test
    public void numIslands() {
        int islands = counter.numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        });

        Assert.assertEquals(3, islands);
    }
}