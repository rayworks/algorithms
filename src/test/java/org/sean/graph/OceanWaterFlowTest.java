package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OceanWaterFlowTest {

    private OceanWaterFlow waterFlow;

    @Before
    public void setUp() throws Exception {
        waterFlow = new OceanWaterFlow();
    }

    @Test
    public void pacificAtlantic() {
        List<List<Integer>> lists = waterFlow.pacificAtlantic(new int[][]{
                {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
        });

        int[][] expected = new int[][]{{0, 4}, {1, 3}, {1, 4}, {2, 2}, {3, 0}, {3, 1}, {4, 0}};
        List<List<Integer>> expectedList = new ArrayList<>();
        for (int[] grp : expected) {
            expectedList.add(Arrays.asList(grp[0], grp[1]));
        }

        assertEquals(expectedList, lists);
    }
}