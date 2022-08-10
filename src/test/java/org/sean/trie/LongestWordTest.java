package org.sean.trie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestWordTest {

    private LongestWord obj;

    @Before
    public void setUp() throws Exception {
        obj = new LongestWord();
    }

    @Test
    public void longestWord() {
        String result = obj.longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"});
        assertEquals("apple", result);
    }
}