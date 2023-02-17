package org.sean.tree;

import org.junit.Before;
import org.junit.Test;
import org.sean.utils.TreeHelper;

import static org.junit.Assert.*;

public class BSTRestorerTest {

    private BSTRestorer restorer;

    @Before
    public void setUp() throws Exception {
        restorer = new BSTRestorer();
    }

    @Test
    public void recoverTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        restorer.recoverTree(root);

        TreeHelper.printLevelTree(root);
        assertEquals(2, root.val);
        assertEquals(3, root.right.left.val);
    }
}