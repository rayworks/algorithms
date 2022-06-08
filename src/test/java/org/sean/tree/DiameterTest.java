package org.sean.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiameterTest {

    private Diameter diameter;

    @Before
    public void setUp() throws Exception {
        diameter = new Diameter();
    }

    @Test
    public void diameterOfBinaryTree() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);

        root.left = left;
        root.right = right;

        assertEquals(3, diameter.diameterOfBinaryTree(root));
    }
}