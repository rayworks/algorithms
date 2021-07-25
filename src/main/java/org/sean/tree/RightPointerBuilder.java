package org.sean.tree;

import java.util.LinkedList;
import java.util.Queue;

// 116. Populating Next Right Pointers in Each Node
public class RightPointerBuilder {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        connectNode(root, false, null);
        return root;
    }

    // DFS (PreOrder traversal) : Time O(N), Space O(1)
    private void connectNode(Node start, boolean fromLeft, Node parent) {
        if (start == null) return;

        if (parent != null) {
            if (fromLeft) start.next = parent.right;
            else start.next = parent.next == null ? null : parent.next.left;
        }

        connectNode(start.left, true, start);
        connectNode(start.right, false, start);
    }

    // BFS : Time O(N), Space O(N)
    public Node connect0(Node root) {
        if (root == null || (root.left == null && root.right == null)) return root;

        Queue<Node> deque = new LinkedList<>();
        deque.add(root);
        Node init = root;
        while (!deque.isEmpty()) {
            int rowSize = deque.size();
            Node pre = null;

            for (int i = 0; i < rowSize; i++) {
                Node node = deque.poll();
                if (node.left == null && node.right == null) {
                    // System.out.println(">>> node : " + node.val);
                    // leaf nodes
                    if (pre == null) pre = node;
                    else {
                        pre.next = node;
                        pre = node;
                    }
                    continue;
                }
                if (node.left != null) {
                    deque.add(node.left);
                    if (pre == null) {
                        pre = node.left;
                    } else {
                        pre.next = node.left;
                        pre = node.left;
                    }
                }
                if (node.right != null) {
                    deque.add(node.right);
                    if (pre != null) { // check for the full binTree
                        pre.next = node.right;
                        pre = node.right;
                    }
                }
            }
        }
        return init;
    }
}
