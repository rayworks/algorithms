package org.sean.graph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PondSizeTest {

    private PondSize pond;

    @Before
    public void setUp() throws Exception {
        pond = new PondSize();
    }

    @Test
    public void size() {
        int[] size = pond.size(new int[][]{
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        });
        Assert.assertArrayEquals(new int[]{2, 4, 1}, size);
    }
}