package org.sean.trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrieTest {

    private Trie trie;

    @Before
    public void setup() {
        trie = new Trie();

        trie.addWord("b");
        trie.addWord("bad");
        trie.addWord("dad");
        trie.addWord("mad");
    }

    @Test
    public void testTrie() {
        Assert.assertTrue(trie.search("b"));
        Assert.assertFalse(trie.search("pad"));
        Assert.assertTrue(trie.search("bad"));
        Assert.assertTrue(trie.search(".ad"));
        Assert.assertTrue(trie.search("b.."));
        Assert.assertFalse(trie.search("ba"));
        Assert.assertFalse(trie.search("b..df"));
    }
}
