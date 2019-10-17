package org.sean.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepeatedSubstrFinderTest {

    private RepeatedSubstrFinder finder;

    @Before
    public void setup() {
        finder = new RepeatedSubstrFinder();
    }

    @Test
    public void repeatedSubstringPattern() {
        Assert.assertTrue(finder.repeatedSubstringPattern("abaababaab"));

        Assert.assertTrue(finder.repeatedSubstringPattern("abcabcabcabc"));

        Assert.assertTrue(finder.repeatedSubstringPattern(
                "babbaaabbbbabbaaabbbbabbaaabbbbabbaaabbbbabbaaabbbbabbaaabbbbabbaaabbbbabbaaabbbbabbaaabbbbabbaaabbb"));
    }

    @Test
    public void testNormal() {
        Assert.assertFalse(finder.repeatedSubstringPattern("abac"));
    }
}
