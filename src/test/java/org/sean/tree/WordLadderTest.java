package org.sean.tree;

import junit.framework.TestCase;

import java.util.Arrays;

public class WordLadderTest extends TestCase {

    private WordLadder wordLadder;

    public void setUp() throws Exception {
        super.setUp();
        wordLadder = new WordLadder();
    }

    public void testLadderLength() {
        int length = wordLadder.ladderLength("hit", "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        assertEquals(5, length);
    }
}