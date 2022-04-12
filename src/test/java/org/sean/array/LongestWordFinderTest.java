package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LongestWordFinderTest {

    private LongestWordFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new LongestWordFinder();
    }

    @Test
    public void findLongestWord() {
        String longestWord = finder.findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea"));
        assertEquals("apple", longestWord);
    }

    @Test
    public void testSingle() {
        String longestWord = finder.findLongestWord("abpcplea", Arrays.asList("a", "b", "c"));
        assertEquals("a", longestWord);
    }
}