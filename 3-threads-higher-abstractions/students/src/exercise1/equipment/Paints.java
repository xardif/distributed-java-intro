package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Paints {
    private int available = 3;
    private Lock lock;
    private Condition condition;

    public Paints() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void takePaint() throws InterruptedException {
        lock.lock();
        try{
            if (available == 0) {
                condition.await();
                //throw new IllegalStateException("There are no more paints!");
            }
            available -= 1;
        } finally {
            lock.unlock();
        }
    }

    public void returnPaint() {
        lock.lock();
        try {
            available += 1;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
