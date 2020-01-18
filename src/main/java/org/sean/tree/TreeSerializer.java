package org.sean.tree;

import org.sean.utils.TreeHelper;

import java.util.*;

/**
 * * 297. Serialize and Deserialize Binary Tree
 *
 * <p>Note: The first solution (BFS) based on the sequence of the node is flawed if depth of the
 * tree is larger than 31. In that case, the sequence number will be at least 2 ^ 32 which is out of
 * range for an Integer.
 */
public class TreeSerializer {

    // [Wrong Answer]
    // ============ First try: ============
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        if (root == null) return "[null]";

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> sequence = new LinkedList<>();

        queue.add(root);
        sequence.add(1);

        StringBuilder builder = new StringBuilder();
        final char and = '&';
        final char sep = ',';

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            int seq = sequence.remove();

            builder.append(seq).append(and).append(node.val).append(sep);

            if (node.left != null) {
                queue.add(node.left);
                sequence.add(seq * 2);
            }
            if (node.right != null) {
                queue.add(node.right);
                sequence.add(seq * 2 + 1);
            }
        }

        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (data.equals("[null]")) return null;

        Map<Integer, TreeNode> map = new HashMap<>();
        String[] splits = data.split(",");
        System.out.println(Arrays.toString(splits));

        List<Integer> orders = new LinkedList<>();
        for (String pair : splits) {
            String[] arr = pair.split("&");
            int order = Integer.parseInt(arr[0]);
            orders.add(order);
            map.put(order, new TreeNode(Integer.parseInt(arr[1])));
        }

        for (Integer order : orders) {
            map.get(order).left = map.get(order * 2);
            map.get(order).right = map.get(order * 2 + 1);
        }

        return map.get(1);
    }
    // ============ End of the First try: ============

    // Solution based on Pre-Order traversal : O(N)
    // input :  "[1,2,3,null,null,4,5]" -> "1,2,*,*,3,4,*,*,5,*,*,"
    private void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }

        builder.append(root.val).append(',');
        if (root.left != null) {
            serialize(root.left, builder);
        } else {
            builder.append('*').append(',');
        }

        if (root.right != null) {
            serialize(root.right, builder);
        } else {
            builder.append('*').append(',');
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        serialize(root, stringBuilder);
        return stringBuilder.toString();
    }

    private void deserialize(Deque<String> queue, TreeNode p) {
        while (!queue.isEmpty()) {
            String str = queue.remove();
            if (!str.equals("*")) {
                TreeNode node = new TreeNode(Integer.parseInt(str));
                deserialize(queue, node);

                p.left = node;
            } else {
                p.left = null;
            }

            str = queue.remove();
            if (!str.equals("*")) {
                TreeNode node = new TreeNode(Integer.parseInt(str));
                deserialize(queue, node);

                p.right = node;
            } else {
                p.right = null;
            }

            break; // break from this subtree
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;

        String[] strs = data.split(",");
        LinkedList<String> queue = new LinkedList<>();
        Collections.addAll(queue, strs);

        String s = queue.remove();
        TreeNode head = new TreeNode(Integer.parseInt(s));
        deserialize(queue, head);

        return head;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n3.left = n4;
        n3.right = n5;

        head.left = n2;
        head.right = n3;

        TreeSerializer serializer = new TreeSerializer();
        String data = serializer.serialize(head);
        System.out.println(data);

        TreeNode treeNode = serializer.deserialize(data);
        TreeHelper.printLevelTree(treeNode);
    }
}
