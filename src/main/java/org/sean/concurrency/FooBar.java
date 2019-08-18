package org.sean.concurrency;

/***
 * 1115 Print FooBar Alternately
 */
public class FooBar {
    private int n;

    private volatile int order = 0;

    private final Object lock = new Object();

    public FooBar(int n) {
        this.n = n;
    }

    private synchronized void update() {
        ++order;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (order % 2 != 0) {
                    lock.wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                update();

                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (order % 2 == 0) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                update();

                lock.notify();
            }
        }
    }
}
