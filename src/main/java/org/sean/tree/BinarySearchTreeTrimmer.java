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

    private TreeNode target = null;

    private void retrieve(TreeNode p, TreeNode root, int key, boolean fromLeftLeaf) {
        if (root != null) {
            if (target != null) return;
            retrieve(root, root.left, key, true);

            if (root.val == key) {
                target = root;

                if (target.left == null && target.right == null) {
                    if (p != null) {
                        if (fromLeftLeaf) p.left = null;
                        else p.right = null;
                    }
                } else if (target.left == null || target.right == null) {
                    if (target.left == null) { // find right min node
                        TreeNode minRight = findMinRight(target.right, target.right.left);
                        if (minRight == target.right) {
                            target.right = null;
                        }
                        target.val = minRight.val;
                    } else { // find left max node
                        TreeNode maxLeft = findMaxLeft(target.left, target.left.right);
                        if (maxLeft == target.left) {
                            target.left = null;
                        }
                        target.val = maxLeft.val;
                    }
                } else { // non empty children
                    TreeNode minRight = findMinRight(target, target.right);
                    target.val = minRight.val;
                }
                return;
            }

            if (target != null) return;
            retrieve(root, root.right, key, false);
        }
    }

    private TreeNode findMaxLeft(TreeNode parent, TreeNode node) {
        while (node != null) {
            if (node.right == null) {
                parent.right = node.left;
                return node;
            }
            parent = node;
            node = node.right;
        }
        return parent;
    }

    private TreeNode findMinRight(TreeNode parent, TreeNode node) {
        while (node != null) {
            if (node.left == null) {
                parent.left = node.right;
                return node;
            }
            parent = node;
            node = node.left;
        }
        return parent;
    }

    /***
     * 450. Delete Node in a BST
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null || (root.val == key && root.left == null && root.right == null))
            return null;
        else {
            retrieve(null, root, key, true);
            return root;
        }
    }
}
