package org.sean.recursive;

import org.sean.tree.TreeNode;

// 1448. Count Good Nodes in Binary Tree
public class GoodNodeCounter {
    private int cnt;

    private void checkNode(TreeNode node, int maxSoFar) {
        if (node != null) {
            if (node.val >= maxSoFar) {
                ++cnt;
            }

            int max = Math.max(node.val, maxSoFar);
            checkNode(node.left, max);
            checkNode(node.right, max);
        }
    }

    public int goodNodes(TreeNode root) {
        checkNode(root, root.val);
        return cnt;
    }
}
