package org.sean.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeValidatorTest {

    private BinaryTreeValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new BinaryTreeValidator();
    }

    @Test
    public void validateBinaryTreeNodes() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(3);

        boolean result = validator.validateBinaryTreeNodes(4, new int[]{3, -1, 1, -1},
                new int[]{-1, -1, 0, -1});
        assertTrue(result);
    }
}