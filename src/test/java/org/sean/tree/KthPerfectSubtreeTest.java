package org.sean.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KthPerfectSubtreeTest {

    private KthPerfectSubtree obj;

    @Before
    public void setUp() throws Exception {
        obj = new KthPerfectSubtree();
    }

    @Test
    public void locateKthLargestSubtree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.right = new TreeNode(8);
        root.left.left = node;
        root.left.right = new TreeNode(2);

        root.right.right = new TreeNode(7);
        node = new TreeNode(5);
        node.left = new TreeNode(6);
        node.right = new TreeNode(8);
        root.right.left = node;

        int size = obj.kthLargestPerfectSubtree(root, 2);
        Assert.assertEquals(3, size);
    }
}