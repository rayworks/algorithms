package org.sean.backtracking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CombinationSum3Test {

    private CombinationSum3 sum3;

    @Before
    public void setUp() throws Exception {
        sum3 = new CombinationSum3();
    }

    @Test
    public void combinationSum3() {
        List<List<Integer>> lists = sum3.combinationSum3(3, 7);
        Assert.assertEquals(Arrays.asList(Arrays.asList(1,2,4)), lists);
    }
}