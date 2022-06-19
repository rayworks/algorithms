package org.sean.trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrieExTest {

    private TrieEx trieEx;

    @Before
    public void setup() {
        trieEx = new TrieEx();

        trieEx.addWord("b");
        trieEx.addWord("bad");
        trieEx.addWord("dad");
        trieEx.addWord("mad");
    }

    @Test
    public void testTrie() {
        Assert.assertTrue(trieEx.search("b"));
        Assert.assertFalse(trieEx.search("pad"));
        Assert.assertTrue(trieEx.search("bad"));
        Assert.assertTrue(trieEx.search(".ad"));
        Assert.assertTrue(trieEx.search("b.."));
        Assert.assertFalse(trieEx.search("ba"));
        Assert.assertFalse(trieEx.search("b..df"));
    }
}
