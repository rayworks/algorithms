package org.sean.recursive;

import org.sean.tree.TreeNode;

/***
 * 124. Binary Tree Maximum Path Sum
 */
public class MaxPathCalculator {
    int max = Integer.MIN_VALUE;

    class Pair {
        public TreeNode node;
        public int val;

        Pair(TreeNode node, int v) {
            this.node = node;
            this.val = v;
        }
    }

    public Pair maxSum(TreeNode root) {
        if (root == null)
            return new Pair(null, 0);

        Pair left = maxSum(root.left);
        Pair right = maxSum(root.right);

        int maxChildValue;
        int childrenSum;
        int currentMax;
        int nodeMax;

        if (left.node != null || right.node != null) {
            if (left.node != null) {
                maxChildValue = left.val;
                childrenSum = maxChildValue;
                if (right.node != null) {
                    maxChildValue = Math.max(maxChildValue, right.val);
                    childrenSum = left.val + right.val;
                }

            } else {
                maxChildValue = right.val;
                childrenSum = maxChildValue;
            }

            // global max:
            // { left, right, Max{left, right} + current.val, current.val, left + right + current.val }
            int s1 = Math.max(maxChildValue + root.val, maxChildValue);
            int s2 = root.val;
            int s3 = childrenSum + root.val;

            currentMax = Math.max(Math.max(s1, s2), s3);

            // The max value returned for current node :
            // Max {current.val, Max{left, right} + current.val}
            nodeMax = Math.max(maxChildValue + root.val, s2);
        } else {
            currentMax = root.val;
            nodeMax = currentMax;
        }
        max = Math.max(max, currentMax);

        return new Pair(root, nodeMax);
    }

    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return max;
    }
}
