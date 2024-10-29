package org.sean.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubtreeFinderTest {

    private SubtreeFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new SubtreeFinder();
    }

    @Test
    public void findSubtreeSizes() {
        int[] res = finder.findSubtreeSizes(new int[]{-1, 0, 0, 1, 1, 1}, "abaabc");
        Assert.assertArrayEquals(new int[]{6, 3, 1, 1, 1, 1}, res);
    }
}