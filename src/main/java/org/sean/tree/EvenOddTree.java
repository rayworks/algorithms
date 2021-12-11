package org.sean.tree;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 1609. Even Odd Tree
 */
public class EvenOddTree {
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val % 2 == 1;

        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int cnt = queue.size();

            boolean ascent = level % 2 == 0;
            int last = -1;
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                // System.out.print(" " + node.val);

                if (node != null) {
                    if (ascent) {
                        if (last > 0 && last >= node.val || node.val % 2 == 0) return false;
                        last = node.val;
                    } else {
                        if (last > 0 && last <= node.val || node.val % 2 == 1) return false;
                        last = node.val;
                    }

                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }
            ++level;

            // System.out.println("");
        }

        return true;
    }
}
