package exercise3;

import common.Counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {

    private AtomicLong a = new AtomicLong();

    @Override
    public void increment() {
        a.incrementAndGet();
    }

    @Override
    public long getValue() {
        return a.longValue();
    }
}
