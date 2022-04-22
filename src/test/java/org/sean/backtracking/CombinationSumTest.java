package org.sean.backtracking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CombinationSumTest {

    private CombinationSum sum;

    @Before
    public void setUp() throws Exception {
        sum = new CombinationSum();
    }

    @Test
    public void combinationSum() {
        ArrayList<List<Integer>> actual = new ArrayList<>();
        actual.add(Arrays.asList(2,2,2,2));
        actual.add(Arrays.asList(2,3,3));
        actual.add(Arrays.asList(3,5));

        List<List<Integer>> out = sum.combinationSum(new int[]{2, 3, 5}, 8);
        assertEquals(actual, out);
    }
}