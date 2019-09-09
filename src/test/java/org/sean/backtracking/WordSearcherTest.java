package org.sean.backtracking;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordSearcherTest {

    char[][] bd = null;
    String word;
    boolean found;

    WordSearcher wordSearcher = new WordSearcher();

    @Test
    public void exist() {
        bd =
                new char[][] {
                    {'A', 'B', 'C', 'E'},
                    {'S', 'F', 'C', 'S'},
                    {'A', 'D', 'E', 'E'}
                };

        word = "ECCBASADF";
        found = wordSearcher.exist(bd, word);
        System.out.println(String.format(">>> word : %s %b", word, found));
        Assert.assertTrue(found);

        word = "SEECS";
        found = wordSearcher.exist(bd, word);
        System.out.println(String.format(">>> word : %s %b", word, found));

        word = "ABCCED";
        found = wordSearcher.exist(bd, word);
        System.out.println(String.format(">>> word : %s %b", word, found));

        word = "ABCB";
        found = wordSearcher.exist(bd, word);
        System.out.println(String.format(">>> word : %s %b", word, found));
    }

    @Test
    public void testComplexCell() {
        bd =
                new char[][] {
                    {'b', 'a', 'a', 'b', 'a', 'b'},
                    {'a', 'b', 'a', 'a', 'a', 'a'},
                    {'a', 'b', 'a', 'a', 'a', 'b'},
                    {'a', 'b', 'a', 'b', 'b', 'a'},
                    {'a', 'a', 'b', 'b', 'a', 'b'},
                    {'a', 'a', 'b', 'b', 'b', 'a'},
                    {'a', 'a', 'b', 'a', 'a', 'b'}
                };
        word = "aabbbbabbaababaaaabababbaaba";
        found = wordSearcher.exist(bd, word);
        System.out.println(String.format(">>> word : %s %b", word, found));
        Assert.assertTrue(found);
    }
}
