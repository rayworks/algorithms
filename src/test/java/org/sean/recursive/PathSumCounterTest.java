package org.sean.recursive;

import org.junit.Assert;
import org.junit.Test;
import org.sean.tree.TreeNode;
import org.sean.utils.TreeHelper;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PathSumCounterTest {

    @Test
    public void pathSum() {
        String[] values = {"1", "-2", "-3", "1", "3", "-2", "null", "-1"};

        TreeNode root = TreeHelper.buildTreeFrom(values);

        PathSumCounter psc = new PathSumCounter();
        int pathSum = psc.pathSum(root, 0);
        Assert.assertEquals(2, pathSum);
    }
}