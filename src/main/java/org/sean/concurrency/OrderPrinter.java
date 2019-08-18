package org.sean.concurrency;

/***
 * 1114. Print in Order
 */
public class OrderPrinter {
    private volatile int order = 0;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private synchronized void updateOrder() {
        ++order;
    }

    public OrderPrinter() {}

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        updateOrder();

        synchronized (lock1) {
            lock1.notify();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock1) {
            while (order != 1) {
                lock1.wait();
            }
            printSecond.run();

            updateOrder();
        }

        synchronized (lock2) {
            lock2.notify();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock2) {
            while (order != 2) {
                lock2.wait();
            }

            updateOrder();

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    private void print(String str) {
        System.out.print(str);
    }

    public static void main(String[] args) throws InterruptedException {
        OrderPrinter printer = new OrderPrinter();
        Thread t1 =
                new Thread(
                        () -> {
                            try {
                                printer.first(() -> printer.print("first"));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });

        t1.join();

        Thread t2 =
                new Thread(
                        () -> {
                            try {
                                printer.second(() -> printer.print("second"));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });

        t2.join();

        Thread t3 =
                new Thread(
                        () -> {
                            try {
                                printer.third(() -> printer.print("third"));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });

        t3.join();

        t3.start();
        t2.start();
        t1.start();
    }
}
