package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ListIntersectionTest {

    private ListIntersection listIntersection;

    @Before
    public void setUp() throws Exception {
        listIntersection = new ListIntersection();
    }

    @Test
    public void intervalIntersection() {
        int[][] out = listIntersection.intervalIntersection(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}}
        );
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] pair : out) {
            list.add(pair[0]);
            list.add(pair[1]);
        }
        assertArrayEquals(new int[]{
                1, 2, 5, 5, 8, 10, 15, 23, 24, 24, 25, 25
        }, list.stream().mapToInt(value -> value).toArray());

        out = listIntersection.intervalIntersection(new int[][]{{1, 3}, {5,9}}, new int[][]{});
        assertArrayEquals(out, Collections.emptyList().toArray());
    }
}