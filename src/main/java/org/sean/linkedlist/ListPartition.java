package org.sean.linkedlist;

/***
 * 86. Partition List
 */
public class ListPartition {
    public static class ListNode {
        int val;
        public ListNode next;

        ListNode() {}

        public ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        // it may contain duplicate nodes
        ListNode prev = head, nHeader = head;
        ListNode curr = nHeader.next;

        ListNode node;
        boolean moved = false;

        while (curr != null) {
            if (curr.val < x) {
                prev.next = curr.next;

                node = nHeader;
                moved = false;

                // search from the beginning, insert current min node [... 0, ^curr, x ...]
                while (node != null && node != prev) {
                    if (node.next != null && node.val < x && node.next.val >= x) {
                        ListNode tmp = node.next;
                        node.next = curr;
                        curr.next = tmp;

                        moved = true;

                        break;
                    }
                    node = node.next;
                }
                if (!moved) {
                    if (nHeader.val >= x) { // insert the min element to the header
                        curr.next = nHeader;
                        if (nHeader.next == curr) { // get rid of circle
                            nHeader.next = null;
                        }
                        nHeader = curr;
                        moved = true;

                    } else { // already on the tail, all less than x
                        prev.next = curr;
                    }
                }

                if (moved) {
                    // new pos on right side
                    curr = prev.next;
                    continue;
                }
            }
            prev = curr;
            curr = curr.next;
        }

        return nHeader;
    }
}
