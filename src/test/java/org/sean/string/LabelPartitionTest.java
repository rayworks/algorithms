package org.sean.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LabelPartitionTest {
    LabelPartition lp;

    @Before
    public void setup() {
        lp = new LabelPartition();
    }

    @Test
    public void testPartition() {
        List<Integer> list = lp.partitionLabels("abaccbdeffed");
        Assert.assertArrayEquals(new Integer[] {6, 6}, list.toArray());
    }
}
