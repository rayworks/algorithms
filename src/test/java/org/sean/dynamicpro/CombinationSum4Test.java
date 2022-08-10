package org.sean.dynamicpro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CombinationSum4Test {

    private CombinationSum4 comb;

    @Before
    public void setUp() throws Exception {
        comb = new CombinationSum4();
    }

    @Test
    public void combinationSum4() {
        int cnt = comb.combinationSum4(new int[]{1, 2, 3}, 32);
        assertEquals(181997601, cnt);
    }
}