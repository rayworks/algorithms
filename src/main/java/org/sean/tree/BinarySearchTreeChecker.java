package org.sean.tree;

/**
 * Created by Sean on 11/13/16.
 * <p/>
 * Update from CTCI 5e
 */
public class BinarySearchTreeChecker {

    public boolean isBST(TreeNode tree) {
        return isBSTree(tree, null, null);
    }

    public boolean isBSTree(TreeNode tree, Integer min, Integer max) {
        if (tree == null) {
            return true;
        }

        if ((min != null && tree.val < min) || (max != null && tree.val >= max)) {
            return false;
        }

        return isBSTree(tree.left, min, tree.val) && isBSTree(tree.right, tree.val, max);

    }
}
