package org.sean.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.sean.utils.MathLib.log2;

/***
 * 637. Average of Levels in Binary Tree
 *
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 */
public class AvgLevelResolver {
    /***
     * @see {@link LevelTraversalSolution}
     */
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
            //++count;

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


    private void addAvgValue(LinkedList<Double> result, List<Integer> target) {
        int cnt = target.size();

        double total = .0;
        for (Integer i : target) {
            total += i;
        }
        result.add(total / cnt);
    }

    public List<Double> averageOfLevels(TreeNode root) {
        LinkedList<Double> result = new LinkedList<>();

        List<List<Integer>> elemList = levelOrderBottom(root);

        int len = elemList.size();
        for (int j = len - 1; j >= 0; j--) {
            List<Integer> list = elemList.get(j);

            addAvgValue(result, list);
        }

        return result;
    }
}
