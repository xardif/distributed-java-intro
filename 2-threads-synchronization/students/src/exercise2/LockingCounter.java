package exercise2;

import common.Counter;

import java.util.concurrent.locks.ReentrantLock;

public class LockingCounter implements Counter {

    private long a = 0;
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void increment() {
        reentrantLock.lock();
        try {
            a += 1;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public long getValue() {
        return a;
    }
}
