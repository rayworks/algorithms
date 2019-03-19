package org.sean.tree;

/***
 * 814. Binary Tree Pruning
 */
public class BinaryTreePruner {
    private boolean bePrunable(TreeNode node) {
        if(node != null) {
            boolean leftPrunable = true;
            boolean rightPrunable = true;
            if(node.left != null) {
                leftPrunable = bePrunable(node.left);
            }

            if(node.right != null) {
                rightPrunable = bePrunable(node.right);
            }

            if(leftPrunable) {
                node.left = null;
            }
            if(rightPrunable) {
                node.right = null;
            }

            if(node.val == 0) {
                if(leftPrunable && rightPrunable)
                    return true;
            }
            return false;
        }

        return true;
    }

    public TreeNode pruneTree(TreeNode root) {
        if(root != null) {
            if(bePrunable(root)) {
                root = null;
            }
        }

        return root;
    }
}
