package org.sean.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.sean.utils.MathLib.log2;

/***
 * 103. Binary Tree Zigzag Level Order Traversal
 */
public class ZigzagTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resultList = new LinkedList<List<Integer>>();
        if (root == null) return resultList;

        if (root.left == null && root.right == null) {
            List<Integer> list = Arrays.asList(root.val);
            resultList.add(list);
            return resultList;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> sequences = new LinkedList<>();

        queue.add(root);
        sequences.add(1);

        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            int val = node.val;
            int count = sequences.pop();

            int index = log2(count);
            if (resultList.size() < 1 + index) {
                List<Integer> list = new LinkedList<>();
                list.add(val);

                resultList.add(list);
            } else {
                // elements order varies for different level
                if (index % 2 == 0) resultList.get(index).add(val);
                else resultList.get(index).add(0, val);
            }

            if (node.left != null) {
                queue.add(node.left);
                sequences.add(2 * count);
            }

            if (node.right != null) {
                queue.add(node.right);
                sequences.add(2 * count + 1);
            }
        }

        return resultList;
    }
}
