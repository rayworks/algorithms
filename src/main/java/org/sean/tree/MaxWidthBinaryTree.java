package org.sean.tree;

import java.util.LinkedList;
import java.util.Queue;

import static org.sean.utils.MathLib.log2;

/***
 * 662. Maximum Width of Binary Tree
 */
public class MaxWidthBinaryTree {
    Queue<TreeNode> queue = new LinkedList<>();
    private int max = 1;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        LinkedList<Integer> resultList = new LinkedList<>();
        LinkedList<Integer> sequences = new LinkedList<>();
        sequences.add(1);

        int count = 0;
        queue.add(root);

        int preDepth = -1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            count = sequences.remove();
            if (node != null) {
                int depth = log2(count);
                if (preDepth != depth) {

                    updateMaxWidth(resultList);

                    resultList.clear();

                    resultList.add(count);
                    preDepth = depth;
                } else {
                    resultList.add(count);
                }

                if (node.left != null) {
                    sequences.add(count * 2);
                    queue.add(node.left);
                }

                if (node.right != null) {
                    sequences.add(count * 2 + 1);
                    queue.add(node.right);
                }
            }
        }

        // For the last level
        if (!resultList.isEmpty()) {
            updateMaxWidth(resultList);
        }

        return max;
    }

    private void updateMaxWidth(LinkedList<Integer> resultList) {
        int size = resultList.size();
        if (size > 1) {
            int distance = resultList.get(size - 1) - resultList.get(0) + 1;
            if (distance > max) {
                max = distance;
            }
        }
    }
}
