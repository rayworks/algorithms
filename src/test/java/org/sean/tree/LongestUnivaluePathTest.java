package org.sean.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestUnivaluePathTest {

    private LongestUnivaluePath univaluePath;

    @Before
    public void setUp() throws Exception {
        univaluePath = new LongestUnivaluePath();
    }

    @Test
    public void longestUnivaluePath() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        int len = univaluePath.longestUnivaluePath(root);
        assertEquals(2, len);
    }
}