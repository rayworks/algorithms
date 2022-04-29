package org.sean.backtracking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubsetPartitionTest {

    private SubsetPartition partition;

    @Before
    public void setUp() throws Exception {
        partition = new SubsetPartition();
    }

    @Test
    public void canPartitionKSubsets() {
        boolean found = partition.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4);
        assertTrue(found);
    }
}