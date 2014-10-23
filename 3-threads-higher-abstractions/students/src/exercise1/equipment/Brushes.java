package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Brushes {
    private int available = 3;
    private Lock lock;
    private Condition condition;

    public Brushes() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void takeBrush() throws InterruptedException {
        lock.lock();
        try {
            if (available == 0) {
                condition.await();
            }
            available -= 1;
        } finally {
            lock.unlock();
        }
    }

    public void returnBrush() {
        lock.lock();
        try {
            available += 1;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
