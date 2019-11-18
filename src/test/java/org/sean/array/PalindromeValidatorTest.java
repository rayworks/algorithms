package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeValidatorTest {

    private PalindromeValidator validator;

    @Before
    public void setup() {
        validator = new PalindromeValidator();
    }
    @Test
    public void validSimplePalindrome() {
        assertFalse(validator.validPalindrome("macccc"));

        assertTrue(validator.validPalindrome("abca"));

        assertTrue(validator.validPalindrome("eccer"));
    }

    @Test
    public void validComplexPalindrome() {
        String str = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        assertTrue(validator.validPalindrome(str));
    }
}