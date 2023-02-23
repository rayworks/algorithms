package org.sean.divide_conquer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SkyLinePainterTest {

    private SkyLinePainter painter;

    @Before
    public void setUp() throws Exception {
        painter = new SkyLinePainter();
    }

    @Test
    public void getSkyline() {
        List<List<Integer>> skyline = painter.getSkyline(new int[][]{
                {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8},
        });

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 10));
        expected.add(Arrays.asList(3, 15));
        expected.add(Arrays.asList(7, 12));
        expected.add(Arrays.asList(12, 0));
        expected.add(Arrays.asList(15, 10));
        expected.add(Arrays.asList(20, 8));
        expected.add(Arrays.asList(24, 0));

        Assert.assertEquals(expected, skyline);
    }
}