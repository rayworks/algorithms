package org.sean.recursive;

import org.sean.tree.TreeNode;

/***
 * 112. Path Sum
 */
public class RootLeafPathFinder {
    private TreeNode top;
    private boolean findPathSum(TreeNode node, int sum, int curr) {

        int value = curr + node.val;
        if(node.left == null && node.right == null) {
            return (value == sum);
        }else {
            boolean leftFound = false;
            if(node.left != null) {
                leftFound = findPathSum(node.left, sum, value);
            }

            boolean rightFound = false;
            if(node.right != null) {
                rightFound = findPathSum(node.right, sum, value);
            }

            return leftFound || rightFound;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;

        int s = root.val;
        if(root.left == null && root.right == null) {
            return s == sum;
        }else {
            top = root;
            return findPathSum(root, sum, 0);
        }
    }
}
