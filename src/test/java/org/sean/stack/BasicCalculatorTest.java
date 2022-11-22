package org.sean.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicCalculatorTest {

    private BasicCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new BasicCalculator();
    }

    @Test
    public void calculate() {
        Assert.assertEquals(23, calculator.calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}