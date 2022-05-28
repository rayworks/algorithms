package org.sean.tree;

/***
 * 687. Longest Univalue Path
 */
public class LongestUnivaluePath {
    // max number of nodes with same value
    private int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;

        traverse(root);

        // the path for max nodes
        return max - 1;
    }

    private int traverse(TreeNode root) {
        if (root != null) {

            // NB: take advantage of each root node
            // update the max along the path : left-parent-right if possible
            // return the single edge with max number of nodes with same value
            int left = traverse(root.left);
            int right = traverse(root.right);

            int sum = 1;
            int curr = root.val;
            // System.out.println("visited -> " + curr + " l :" + left + " r : " + right);

            int l = 0;
            if (root.left != null) {
                if (curr == root.left.val) {
                    sum += left;
                    l += left;
                }
            }

            int r = 0;
            if (root.right != null) {
                if (curr == root.right.val) {
                    sum += right;
                    r += right;
                }
            }

            // System.out.println(curr + " -> sum: " + sum);
            max = Math.max(sum, max);

            return Math.max(1 + l, 1 + r);

        } else {
            return 0;
        }
    }
}
