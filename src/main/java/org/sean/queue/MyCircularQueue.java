package org.sean.queue;

/***
 * 622. Design Circular Queue
 *
 * <p>
 * Implementation of the MyCircularQueue class:
 * </p>
 *
 * <img src="../../../../../../images/buffer.png">
 */
public class MyCircularQueue {
    private final int size;
    private final int[] queue;
    private int tear = 0;
    private int front = 0;

    public MyCircularQueue(int k) {
        if (k <= 0)
            throw new IllegalArgumentException("The queue size should be larger than 0");

        size = k + 1;
        queue = new int[size];
    }

    /***
     * Inserts an element into the circular queue. Return true if the operation is successful.
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        boolean notFull = !isFull();
        if (notFull) {
            queue[tear] = value;
            tear = (tear + 1) % size;
        }
        return notFull;
    }

    /***
     * Deletes an element from the circular queue. Return true if the operation is successful.
     * @return
     */
    public boolean deQueue() {
        boolean notEmpty = !isEmpty();
        if (notEmpty) {
            front = (front + 1) % size;
        }
        return notEmpty;
    }

    /***
     * Gets the front item from the queue. If the queue is empty, return -1.
     * @return
     */
    public int Front() {
        if (isEmpty()) return -1;
        return queue[front % size];
    }

    /***
     * Gets the last item from the queue. If the queue is empty, return -1.
     * @return
     */
    public int Rear() {
        if (isEmpty()) return -1;
        return queue[(tear + size - 1) % size];
    }

    /***
     * Checks whether the circular queue is empty or not.
     * @return
     */
    public boolean isEmpty() {
        return front % size == tear % size;
    }

    /***
     * Checks whether the circular queue is full or not.
     * @return
     */
    public boolean isFull() {
        return (tear + 1) % size == front % size;
    }
}
