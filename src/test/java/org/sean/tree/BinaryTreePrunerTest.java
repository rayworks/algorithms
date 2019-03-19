package org.sean.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sean.utils.TreeHelper;

import static org.junit.Assert.*;

public class BinaryTreePrunerTest {

    private BinaryTreePruner treePruner;

    @Before
    public void setup() {
        treePruner = new BinaryTreePruner();
    }

    @Test
    public void pruneTree() {
        String[] str = {"1", "0", "1", "0", "0", "0", "1"};
        TreeNode node = TreeHelper.buildTreeFrom(str);
        TreeNode pruneTree = treePruner.pruneTree(node);

        String[] nodeStrs = {"1", "null", "1", "null", "null", "null", "1", "null", "null"};
        TreeNode targetTree = TreeHelper.buildTreeFrom(nodeStrs);
        Assert.assertTrue(TreeHelper.isSameTree(pruneTree, targetTree));


        String[] str2 = {"1", "null", "0", "null", "null", "0", "1"};
        pruneTree = treePruner.pruneTree(TreeHelper.buildTreeFrom(str2));

        String[] resultNodes = {"1", "null", "0","null", "null", "null", "1"};
        targetTree = TreeHelper.buildTreeFrom(resultNodes);
        Assert.assertTrue(TreeHelper.isSameTree(pruneTree, targetTree));
    }
}