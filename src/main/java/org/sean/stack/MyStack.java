package org.sean.stack;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 225. Implement Stack using Queues
 */
class MyStack {

    private final Queue<Integer> queue;
    private final Queue<Integer> queueBackup;

    private boolean mainQueueUsed;

    public MyStack() {
        queue = new LinkedList<>();
        queueBackup = new LinkedList<>();
        mainQueueUsed = true;
    }

    public void push(int x) {
        if (mainQueueUsed)
            queue.offer(x);
        else
            queueBackup.offer(x);
    }

    public int pop() {
        return checkTopElem(true);
    }

    private int checkTopElem(boolean needPop) {
        if (mainQueueUsed) {
            mainQueueUsed = !mainQueueUsed;
            while (!queue.isEmpty()) {
                int elem = queue.poll();
                if (queue.isEmpty()) {
                    if (needPop)
                        return elem;
                    else {
                        queueBackup.offer(elem);
                        return elem;
                    }
                } else {
                    queueBackup.offer(elem);
                }
            }
        } else {
            mainQueueUsed = !mainQueueUsed;
            while (!queueBackup.isEmpty()) {
                int elem = queueBackup.poll();
                if (queueBackup.isEmpty()) {
                    if (needPop)
                        return elem;
                    else {
                        queue.offer(elem);
                        return elem;
                    }
                } else
                    queue.offer(elem);
            }
        }
        throw new IllegalStateException("");
    }

    public int top() {
        return checkTopElem(false);
    }

    public boolean empty() {
        return queue.isEmpty() && queueBackup.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
