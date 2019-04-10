package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConsecutiveSequenceFinderTest {

    @Test
    public void longestConsecutive() {
        ConsecutiveSequenceFinder finder = new ConsecutiveSequenceFinder();
        
        int len = finder.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        Assert.assertEquals(4, len);
    }
}