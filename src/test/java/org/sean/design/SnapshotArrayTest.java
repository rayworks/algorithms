package org.sean.design;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnapshotArrayTest {

    @Test
    public void testOperations() throws Exception {
        SnapshotArray array = new SnapshotArray(3);
        array.set(0, 5);
        assertEquals(0, array.snap());

        array.set(0, 6);
        assertEquals(5, array.get(0, 0));
    }
}