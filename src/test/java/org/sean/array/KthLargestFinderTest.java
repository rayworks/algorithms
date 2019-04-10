package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class KthLargestFinderTest {

    @Test
    public void findKthLargest() {
        KthLargestFinder finder = new KthLargestFinder();

        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        Assert.assertEquals(4, finder.findKthLargest(nums, 4));
    }
}