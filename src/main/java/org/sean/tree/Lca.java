package org.sean.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Leetcode 235. Lowest Common Ancestor of a Binary Search Tree
 * <p>
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Lca {
    private LinkedList<Integer> inOrderSet = new LinkedList();
    private LinkedList<TreeNode> preOrderNodes = new LinkedList();

    private void inorderTravel(TreeNode node) {
        if (node != null) {
            inorderTravel(node.left);

            inOrderSet.add(node.val);

            inorderTravel(node.right);
        }
    }

    private void preorderTravel(TreeNode node) {
        if (node != null) {
            preOrderNodes.add(node);

            preorderTravel(node.left);
            preorderTravel(node.right);
        }
    }

    private int locateElem(TreeNode node) {
        int p = 0;
        for (Integer i : inOrderSet) {
            if (node.val == i) {
                break;
            }
            ++p;
        }

        return p;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || (p == q)) {
            return root;
        }

        TreeNode inNode = root;
        TreeNode preNode = root;

        inorderTravel(inNode);
        preorderTravel(preNode);

        int pIndex = locateElem(p);
        int qIndex = locateElem(q);

        int j = pIndex;
        int k = qIndex;
        if (j > k) {
            int tmp = j;
            j = k;
            k = tmp;
        }

        Set<Integer> set = new HashSet<Integer>();
        for (int m = j; m <= k; m++) {
            set.add(inOrderSet.get(m));
        }
        for (TreeNode node : preOrderNodes) {
            if (set.contains(node.val)) {
                return node;
            }
        }

        // as a fallback
        return root;
    }

    // solution2:
    private TreeNode target;
    int lookup(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || target != null) return 0;

        int curr = 0;
        if (node.val == p.val) curr = 1;
        else if (node.val == q.val) curr = 2;

        int left = lookup(node.left, p, q);
        int right = lookup(node.right, p, q);

        if (curr + left + right == 3) {
            if (target == null) target = node;
        }
        return curr + left + right;
    }

    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;

        lookup(root, p, q);
        return target == null ? root : target;
    }
}