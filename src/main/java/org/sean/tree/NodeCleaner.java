package org.sean.tree;

/***
 * 1325. Delete Leaves With a Given Value
 */
public class NodeCleaner {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;

        if (root.left == null && root.right == null && root.val == target) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.left == null && root.right == null && root.val == target) root = null;

        return root;
    }
}
