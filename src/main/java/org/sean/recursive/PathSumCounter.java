package org.sean.recursive;

import org.sean.tree.TreeNode;

import java.util.HashSet;

/***
 * 437. Path Sum III
 */
public class PathSumCounter {
    private int counter;
    private TreeNode top;

    private HashSet<TreeNode> set = new HashSet<TreeNode>();
    private HashSet<TreeNode> selfTravelled = new HashSet<>();
    private void travelTree(TreeNode node, int sum, int currSum) {
        if(node != null) {
            int value = node.val;
            if(value == sum) {
                if(!set.contains(node)) {
                    set.add(node);
                }
            }

            int newSum = currSum + value;

            // middle nodes itself traversal
            if(node != top) {
                // extend from parent
                if(newSum == sum) {
                    ++counter;
                }

                // check duplicate node traversal
                if(!selfTravelled.contains(node)) {
                    travelTree(node.left, sum, value);
                    travelTree(node.right, sum, value);

                    selfTravelled.add(node);
                }
            }

            // normal traversal with added up result
            travelTree(node.left, sum, newSum);
            travelTree(node.right, sum, newSum);
        }
    }

    public int pathSum(TreeNode root, int sum) {
        counter = 0;

        if(root != null) {
            top = root;
            set.clear();

            travelTree(root, sum, 0);
        }

        return counter + set.size();
    }
}
