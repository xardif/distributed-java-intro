package exercise2.equipment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Paints {

    private BlockingQueue<String> queue;

    public Paints() {
        queue = new LinkedBlockingQueue(4);
        for(String color : new String[]{"red", "green", "blue", "black"}){
            queue.add(color);
        }
    }

    public String takePaint() throws InterruptedException {
        return queue.take();
    }

    public void returnPaint(String paint) {
        queue.offer(paint);
    }

    //remove and put throws exceptions
}
