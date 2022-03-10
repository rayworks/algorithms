package org.sean.backtracking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ParenthesesEditorTest {

    private ParenthesesEditor editor;

    @Before
    public void setUp() {
        editor = new ParenthesesEditor();
    }

    @Test
    public void removeInvalidParentheses() {
        List<String> list = editor.removeInvalidParentheses(")(");
        List<String> expected = Arrays.asList("");
        assertTrue(list.containsAll(expected) && expected.containsAll(list));

        list = editor.removeInvalidParentheses("()())()");
        expected = Arrays.asList("(())()", "()()()");
        assertTrue(list.containsAll(expected) && expected.containsAll(list));

        list = editor.removeInvalidParentheses("(a)())()");
        expected = Arrays.asList("(a)()()", "(a())()");
        assertTrue(list.containsAll(expected) && expected.containsAll(list));

        list = editor.removeInvalidParentheses("((((((((((((((((((((aaaaa");
        expected = Arrays.asList("aaaaa");
        assertTrue(list.containsAll(expected) && expected.containsAll(list));
    }
}