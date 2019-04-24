package org.sean.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParenthesesValidatorTest {

    private ParenthesesValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new ParenthesesValidator();
    }

    @Test
    public void isValid() {
        Assert.assertTrue(validator.isValid(""));
        Assert.assertFalse(validator.isValid("([)]"));
        Assert.assertTrue(validator.isValid("{[]}"));
    }
}