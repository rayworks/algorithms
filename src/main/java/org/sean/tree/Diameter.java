package org.sean.tree;

/***
 * 543. Diameter of Binary Tree
 */
public class Diameter {
    int maxPath = 0;

    private int traverseTree(TreeNode root) {
        if (root == null)
            return 0;

        int left = traverseTree(root.left) ;
        int right = traverseTree(root.right);

        // calculates the max number of the path
        maxPath = Math.max(maxPath, left + right + 1);

        // return the longer subtree path
        return Math.max(left, right) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        traverseTree(root);
        return maxPath - 1;
    }
}
