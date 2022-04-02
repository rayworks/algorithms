package org.sean.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NextGreaterNodeTest {

    private NextGreaterNode node;

    @Before
    public void setUp() throws Exception {
        node = new NextGreaterNode();
    }

    @Test
    public void nextLargerNodes() {
        int[] elems = new int[]{2, 7, 4, 3, 5};
        NextGreaterNode.ListNode head = null, p = null;
        for (int i : elems) {
            NextGreaterNode.ListNode listNode = new NextGreaterNode.ListNode(i);
            if (head == null) {
                head = listNode;
                p = listNode;
            } else {
                p.next = listNode;
                p = listNode;
            }
        }
        int[] result = node.nextLargerNodes(head);
        assertArrayEquals(new int[]{7, 0, 5, 5, 0}, result);
    }
}