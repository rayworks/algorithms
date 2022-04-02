package org.sean.linkedlist;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/***
 * 1019. Next Greater Node In Linked List
 */
public class NextGreaterNode {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Monotonic stack : O(N)
    public int[] nextLargerNodes(ListNode head) {
        if (head == null)
            return new int[]{};

        ListNode node = head;

        int cnt = 0;
        while (node != null) {
            cnt++;
            node = node.next;
        }
        int[] result = new int[cnt];

        node = head;
        int index = 0;

        // element : <val, index>
        Deque<int[]> stack = new LinkedList<>();

        while (node != null) {
            int e = node.val;
            if (stack.isEmpty()) {
                stack.push(new int[]{e, index});
            } else {
                int top = stack.peek()[0];
                // decreased num found
                if (top >= e) {
                    stack.push(new int[]{e, index});
                } else {
                    // larger num shows up, update the result for all the smaller nodes
                    while (!stack.isEmpty()) {
                        if (stack.peek()[0] < e) {
                            int[] pair = stack.pop();
                            int pos = pair[1];
                            result[pos] = e;
                        } else {
                            break;
                        }
                    }
                    stack.push(new int[]{e, index});
                }
            }

            node = node.next;
            index++;
        }

        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            int pos = pair[1];
            result[pos] = 0;
        }

        return result;
    }

    // Brute force : O(N^2)
    public int[] nextLargerNodes0(ListNode head) {
        if (head == null)
            return new int[]{};


        ListNode node = head;
        ListNode post;
        List<Integer> list = new ArrayList<>();

        while (node != null) {
            int target = node.val;
            post = node.next;
            while (post != null) {
                if (post.val > target) {
                    break;
                }
                post = post.next;
            }
            list.add(post != null ? post.val : 0);

            node = node.next;
        }
        return list.stream().mapToInt(value -> value).toArray();
    }
}
