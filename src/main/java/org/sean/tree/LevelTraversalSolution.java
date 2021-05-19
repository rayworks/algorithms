package org.sean.tree;

import java.util.*;

import static org.sean.utils.MathLib.log2;

/**
 * Created by Sean on 11/17/16.
 *
 * <p>Leetcode : 107. Binary Tree Level Order Traversal II
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class LevelTraversalSolution {
    public List<List<Integer>> levelOrderBottom0(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);

                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            result.add(0, list);
        }
        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> resultList = new LinkedList<List<Integer>>();
        if (root == null) {
            return resultList;
        }
        if (root.left == null && root.right == null) {
            List<Integer> list = Arrays.asList(root.val);
            resultList.add(list);
            return resultList;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        LinkedList<Integer> sequences = new LinkedList<>();
        sequences.add(1);

        int count = 0;
        while (queue.size() > 0) {
            TreeNode node = queue.remove();
            count = sequences.remove();
            // ++count;

            if (node != null) {
                int index = log2(count);

                // As the list grows step by step, preserve the order when inserting a group
                if (resultList.size() != 1 + index) {
                    List<Integer> lt = new LinkedList<>();
                    lt.add(node.val);

                    resultList.add(0, lt);
                } else {
                    resultList.get(0).add(node.val);
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

        return resultList;
    }
}
