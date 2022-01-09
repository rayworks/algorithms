package org.sean.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListPartitionTest {
    private ListPartition partition;

    @Before
    public void setup() {
        partition = new ListPartition();
    }

    @Test
    public void partition() {
        ListPartition.ListNode node = new ListPartition.ListNode(1);
        ListPartition.ListNode node2 = new ListPartition.ListNode(2);
        ListPartition.ListNode node3 = new ListPartition.ListNode(3);
        node2.next = node3;
        node.next = node2;

        ListPartition.ListNode node1 = partition.partition(node, 3);
        ListPartition.ListNode p = node1;

        int i = 0;
        int[] result = new int[3];
        while (p != null) {
            System.out.println(p.val);

            result[i++] = p.val;
            p = p.next;
        }

        assertArrayEquals(new int[] {1, 2, 3}, result);
    }
}