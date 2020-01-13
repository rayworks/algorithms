package org.sean.tree;

import org.junit.Assert;
import org.junit.Test;
import org.sean.utils.TreeHelper;

public class MaxWidthBinaryTreeTest {

    @Test
    public void widthOfBinaryTree() {
        TreeNode treeNode = TreeHelper.buildTreeFrom(new String[]{"1", "3", "2", "5", "3", "null", "9"});
        Assert.assertEquals(4, new MaxWidthBinaryTree().widthOfBinaryTree(treeNode));
    }
}