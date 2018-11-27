package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PermutationCheckerTest {

    @Test
    public void isPermutation() {
        PermutationChecker checker = new PermutationChecker();
        Assert.assertTrue(checker.isPermutation("asdfa", "sadfa"));

        Assert.assertFalse(checker.isPermutation("asdfk", "sadfa"));
        Assert.assertFalse(checker.isPermutation("asdf", "sadfa"));
    }
}