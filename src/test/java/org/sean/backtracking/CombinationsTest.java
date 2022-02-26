package org.sean.backtracking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CombinationsTest {
    private Combinations combinations;

    @Before
    public void setup() {
        combinations = new Combinations();
    }

    @Test
    public void combine() {
        List<List<Integer>> lists = combinations.combine(4, 2);
        /*for (List<Integer> list : lists) {
            int[] ints = list.stream().mapToInt(value -> value).toArray();
            System.out.println(Arrays.toString(ints));
        }*/
        int[][] expected = {{2, 4}, {3, 4}, {2, 3}, {1, 2}, {1, 3}, {1, 4}};
        List<List<Integer>> exp = new ArrayList<>();
        for (int[] p : expected) exp.add(Arrays.asList(p[0], p[1]));

        assertTrue(lists.containsAll(exp));
    }
}
