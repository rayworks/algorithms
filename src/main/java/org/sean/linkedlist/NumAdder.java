package org.sean.linkedlist;

// 2. Add Two Numbers
public class NumAdder {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Solution 1 :
    public ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        else {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            }

            // both valid
            ListNode pt1 = l1;
            ListNode pt2 = l2;
            ListNode hdr = null, pt = null;

            int carry = 0;
            while (pt1 != null && pt2 != null) {
                int x = pt1.val + pt2.val + carry;
                carry = x / 10;

                ListNode node = new ListNode(x % 10);

                if (hdr == null) {
                    hdr = pt = node;
                    // pt = hdr;
                } else {
                    pt.next = node;
                    pt = node;
                }

                pt1 = pt1.next;
                pt2 = pt2.next;
            }

            ListNode node;
            while (pt1 != null) {
                int x = pt1.val + carry;
                carry = x / 10;
                pt.next = new ListNode(x % 10);

                pt = pt.next;
                pt1 = pt1.next;
            }

            while (pt2 != null) {
                int x = pt2.val + carry;
                carry = x / 10;
                pt.next = new ListNode(x % 10);

                pt = pt.next;
                pt2 = pt2.next;
            }

            if (carry == 1) {
                pt.next = new ListNode(1);
            }

            return hdr;
        }
    }

    // Solution 2 :
    ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) return null;
        ListNode result = new ListNode(0);

        int value = carry;
        if (l1 != null) {
            value += l1.val;
        }
        if (l2 != null) {
            value += l2.val;
        }

        result.val = value % 10;

        ListNode node =
                addTwo(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value / 10);

        result.next = node;

        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwo(l1, l2, 0);
    }
}
