package org.sean.tree;

/***
 * 110. Balanced Binary Tree
 */
public class BalancedBinaryTree {
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // time: O(N*logN)
        boolean left = isBalanced(root.left);
        boolean right = isBalanced(root.right);

        boolean result = Math.abs(height(root.left) - height(root.right)) <= 1;

        return left && right && result;
    }
    // ====================================================================================
    private int checkHeight(TreeNode root) {
        if(root == null)
            return 0;

        int leftHeight = checkHeight(root.left);
        if(leftHeight == -1) {
            return -1; // not balanced
        }

        int rightHeight = checkHeight(root.right);
        if(rightHeight == -1) {
            return -1;
        }

        int diff = leftHeight - rightHeight;
        if(Math.abs(diff) > 1) {
            return  -1;
        }else {
            // return the height
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public boolean isBalanced2(TreeNode root) {
        // time: O(N)
        // space: O(H), H : height of the tree
        return  (checkHeight(root) != -1);
    }
}
