package org.sean.tree;

// 951 Flip Equivalent Binary Trees
public class FlipEquivalentTree {

    private boolean areNodesEqual(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        else if (t1 == null || t2 == null) return false;
        else return t1.val == t2.val;
    }

    private void flip(TreeNode node) {
        if (node != null) {
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
    }

    private boolean isLeaf(TreeNode node) {
        if (node != null) {
            return node.left == null && node.right == null;
        }
        return false;
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else {
            if (root1.val == root2.val) {
                if (isLeaf(root1) && isLeaf(root2)) {
                    return true;
                } else {
                    if (!areNodesEqual(root1.left, root2.left)) {
                        flip(root1);
                    }

                    boolean leftResult = flipEquiv(root1.left, root2.left);
                    boolean rightResult = flipEquiv(root1.right, root2.right);

                    return leftResult && rightResult;
                }
            } else {
                return false;
            }
        }
    }
}
