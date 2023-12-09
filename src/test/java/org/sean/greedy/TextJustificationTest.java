package org.sean.greedy;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TextJustificationTest {

    private TextJustification editor;

    @Before
    public void setUp() throws Exception {
        editor = new TextJustification();
    }

    @Test
    public void fullJustify() {
        List<String> list = editor.fullJustify(new String[]{
                "What", "must", "be", "acknowledgment", "shall", "be"
        }, 16);

        List<String> expected = Arrays.asList(
                "What   must   be",
                "acknowledgment  ",
                "shall be        ");

        assertEquals(expected, list);
    }

    @Test
    public void fullJustifyMoreStrings() {
        List<String> out = editor.fullJustify(new String[]{
                "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.",
                "Art", "is", "everything", "else", "we", "do"
        }, 20);

        List<String> expected = Arrays.asList(
                "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "
        );
        assertEquals(expected, out);
    }
}