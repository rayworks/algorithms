package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KthStreamLargestTest {

    private KthStreamLargest kthStreamLargest;

    @Before
    public void setup() {
        int k = 3;
        int[] arr = {4,5,8,2};
        kthStreamLargest = new KthStreamLargest(3, arr);
    }

    @Test
    public void add() {
        Assert.assertEquals(4, kthStreamLargest.add(3));
        Assert.assertEquals(5, kthStreamLargest.add(5));
        Assert.assertEquals(5, kthStreamLargest.add(10));
        Assert.assertEquals(8, kthStreamLargest.add(9));
        Assert.assertEquals(8, kthStreamLargest.add(4));
    }
}