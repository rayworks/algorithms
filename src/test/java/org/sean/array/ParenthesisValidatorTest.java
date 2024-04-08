package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParenthesisValidatorTest {

    private ParenthesisValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new ParenthesisValidator();
    }

    @Test
    public void checkValidString() {
        assertTrue(validator.checkValidString("(*))"));
    }
}