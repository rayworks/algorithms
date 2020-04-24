package org.sean.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** * 199. Binary Tree Right Side View */
public class BinaryTreeRightSide {
    public List<Integer> rightSideView0(TreeNode root) {
        List<List<Integer>> lists = new AvgLevelResolver().levelOrderBottom(root);

        LinkedList<Integer> result = new LinkedList<>();
        for (List<Integer> list : lists) {
            int size = list.size();
            result.add(0, list.get(size - 1));
        }
        return result;
    }

    // Solution2 : O(N)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 1; i <= n; i++) {
                TreeNode node = queue.remove();
                if (i == n) {
                    result.add(node.val);
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }

    // [m, n] where 0 <= m <= n <= 2147483647
    int[] bits = new int[31];

    int getBit(int num, int i) {
        return num & (1 << i); // 0 | 1
    }

    boolean isZero() {
        for (int i = 0; i < 31; i++) {
            if (bits[i] != 0)
                return false;
        }
        return true;
    }

    int max = 2147483647;
    public int rangeBitwiseAnd(int m, int n) {
        int s = m;
        if (m < max) {
            for (int t = m + 1; t <= n && t < max; t++) {
                s &= t;
                if (s == 0) return 0;
            }
        }
        return s;
    }
}
