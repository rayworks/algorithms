package org.sean.recursive;

import org.junit.Assert;
import org.junit.Test;
import org.sean.tree.TreeNode;
import org.sean.utils.TreeHelper;

import static org.junit.Assert.*;

public class RootLeafPathCalculatorTest {

    @Test
    public void sumNumbers() {

        String[] values = {"4", "9", "0", "5", "1"};
        TreeNode root = TreeHelper.buildTreeFrom(values);

        RootLeafPathCalculator calculator = new RootLeafPathCalculator();
        Assert.assertEquals(1026, calculator.sumNumbers(root));
    }
}