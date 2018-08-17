package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestSubstrFinderTest {

    @Test
    public void lengthOfLongestSubstring() {
        LongestSubstrFinder finder = new LongestSubstrFinder();

        Assert.assertEquals(5, finder.lengthOfLongestSubstring("qrsvbspk"));
        Assert.assertEquals(11, finder.lengthOfLongestSubstring("bziuwnklhqzrxnb"));
        Assert.assertEquals(3, finder.lengthOfLongestSubstring("didv"));
    }
}