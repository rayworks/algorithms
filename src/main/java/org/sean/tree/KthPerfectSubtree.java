package org.sean.tree;

import java.util.PriorityQueue;

/***
 * 3319. K-th Largest Perfect Subtree Size in Binary Tree
 */
public class KthPerfectSubtree {

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        // post traversal
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        traverse(root, pq);

        int cnt = 0;
        while (!pq.isEmpty()) {
            int elem = pq.poll();
            cnt++;
            if (cnt == k) {
                return elem;
            }
        }
        return -1;
    }

    // accumulated size for current tree
    private int traverse(TreeNode node, PriorityQueue<Integer> pq) {
        if (node == null)
            return 0;

        int leftCnt = traverse(node.left, pq);
        int rightCnt = traverse(node.right, pq);

        // quick return
        if (leftCnt == -1 || rightCnt == -1) {
            return -1;
        }

        if (leftCnt == rightCnt) {
            int sz = leftCnt + rightCnt + 1;
            pq.add(sz);

            return sz;
        } else {
            return -1;
        }
    }

}
