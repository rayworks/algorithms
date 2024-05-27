package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NextPermutationTest {

    private NextPermutation perm;

    @Before
    public void setUp() throws Exception {
        perm = new NextPermutation();
    }

    @Test
    public void nextPermutation() {
        int[] nums = {1, 2, 4, 7, 5, 3};
        perm.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 5, 3, 4, 7}, nums);
    }
}