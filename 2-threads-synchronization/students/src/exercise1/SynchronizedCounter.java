package exercise1;

import common.Counter;

public class SynchronizedCounter implements Counter {

    private long a = 0;

    @Override
    public synchronized void increment() {
        a += 1;
    }

    @Override
    public long getValue() {
        return a;
    }
}
