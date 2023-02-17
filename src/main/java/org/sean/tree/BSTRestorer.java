package org.sean.tree;

import java.util.ArrayList;
import java.util.List;

/***
 * 99. Recover Binary Search Tree
 */
public class BSTRestorer {
    // region Space O(1)
    private TreeNode prev;

    private void traverse(TreeNode node) {
        if (node != null) {
            traverse(node.left);

            //    1
            //   /
            // 3
            //  \
            //    2
            if (prev != null && prev.val >= node.val) {
                if (node1 == null)
                    node1 = prev;
                node2 = node;
            }
            prev = node;

            traverse(node.right);
        }
    }

    public void recoverTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return;

        traverse(root);

        if (node1 != null && node2 != null) {
            int val = node1.val;
            node1.val = node2.val;
            node2.val = val;
        }
    }
    // endregion

    // region Space O(N)
    private TreeNode node1 = null, node2 = null;
    private final List<TreeNode> nodes = new ArrayList<>();

    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            nodes.add(node);
            inOrder(node.right);
        }
    }

    public void recoverTree0(TreeNode root) {
        inOrder(root);

        int start = 0;
        int end = nodes.size() - 1;

        while (start < end) {
            int i = start + 1;

            if (nodes.get(start).val >= nodes.get(i).val) { // max
                node1 = nodes.get(start);
                break;
            }
            start++;
        }

        while (end > start) {
            int i = end - 1;
            if (nodes.get(end).val <= nodes.get(i).val) {  // small
                node2 = nodes.get(end);
                break;
            }
            end--;
        }

        if (node1 != null && node2 != null) {
            int tmp = node1.val;
            node1.val = node2.val;
            node2.val = tmp;
        }
    }
    // endregion
}
