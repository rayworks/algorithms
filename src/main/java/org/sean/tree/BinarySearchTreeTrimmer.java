package org.sean.tree;

/** * 669. Trim a Binary Search Tree */
public class BinarySearchTreeTrimmer {
    private TreeNode findNextNode(TreeNode node, int L, int R) {
        if (node == null) {
            return null;
        }

        if (node.val >= L && node.val <= R) return node;

        if (node.val < L) {
            return findNextNode(node.right, L, R);
        } else {
            return findNextNode(node.left, L, R);
        }
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        root = findNextNode(root, L, R);

        if (root != null) root.left = trimBST(root.left, L, R);
        if (root != null) root.right = trimBST(root.right, L, R);

        return root;
    }
}
