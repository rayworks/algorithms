package org.sean.recursive;

import org.junit.Assert;
import org.junit.Test;
import org.sean.tree.TreeNode;
import org.sean.utils.TreeHelper;

import static org.junit.Assert.*;

public class RootLeafPathFinderTest {

    @Test
    public void hasPathSum() {
        String[] values = {"5", "4", "8", "11", "null", "13", "4", "7", "2", "null", "null", "null", "1"};
        TreeNode root = TreeHelper.buildTreeFrom(values);

        RootLeafPathFinder pathFinder = new RootLeafPathFinder();
        Assert.assertTrue(pathFinder.hasPathSum(root, 22));
    }
}