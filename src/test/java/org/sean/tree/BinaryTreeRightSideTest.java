package org.sean.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BinaryTreeRightSideTest {

    private BinaryTreeRightSide binaryTreeRightSide;

    @Before
    public void setUp() throws Exception {
        binaryTreeRightSide = new BinaryTreeRightSide();
    }

    @Test
    public void rightSideView() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        List<Integer> views = binaryTreeRightSide.rightSideView(root);
        Integer[] toArray = views.toArray(new Integer[views.size()]);
        Assert.assertArrayEquals(new Integer[] {1, 3, 4}, toArray);
    }
}
