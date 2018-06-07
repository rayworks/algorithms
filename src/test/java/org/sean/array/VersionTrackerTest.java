package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class VersionTrackerTest {
    @Test
    public void firstBadVersion() throws Exception {
        VersionTracker tracker = new VersionTracker();

        int n = 921239930;
        int num = tracker.firstBadVersion(n);

        Assert.assertEquals(823161944, num);
    }

}