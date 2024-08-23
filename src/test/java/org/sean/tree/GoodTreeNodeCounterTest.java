package org.sean.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodTreeNodeCounterTest {

    private GoodTreeNodeCounter counter;

    @Before
    public void setUp() throws Exception {
        counter = new GoodTreeNodeCounter();
    }

    @Test
    public void countGoodNodes() {
        int res = counter.countGoodNodes(new int[][]{
                {0, 1}, {1, 2}, {2, 3}, {3, 4}, {0, 5}, {1, 6}, {2, 7}, {3, 8}
        });
        assertEquals(6, res);
    }
}