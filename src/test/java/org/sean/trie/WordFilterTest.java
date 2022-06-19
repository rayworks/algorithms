package org.sean.trie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class WordFilterTest {

    private WordFilter wordFilter;

    @Before
    public void setUp() throws Exception {
        wordFilter = new WordFilter(new String[] {
                "cabaabaaaa", "ccbcababac", "bacaabccba", "bcbbcbacaa", "abcaccbcaa", "accabaccaa", "cabcbbbcca", "ababccabcb",
                "caccbbcbab", "bccbacbcba"
        });
    }


    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"bccbacbcba", "a", 9}, {"ab", "abcaccbcaa", 4}, {"a", "aa", 5}, {"cabaaba", "abaaaa", 0}, {"cacc", "accbbcbab", 8},
                {"ccbcab", "bac", 1}, {"bac", "cba", 2}, {"ac", "accabaccaa", 5}, {"bcbb", "aa", 3}, {"ccbca", "cbcababac", 1}
        });
    }

    @Parameterized.Parameter(0)
    public String prefix;

    @Parameterized.Parameter(1)
    public String suffix;

    @Parameterized.Parameter(2)
    public int pos;

    @Test
    public void f() {
        assertEquals(pos, wordFilter.f(prefix, suffix));
    }
}