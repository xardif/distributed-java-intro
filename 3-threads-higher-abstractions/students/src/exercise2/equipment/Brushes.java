package exercise2.equipment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Brushes {

    private BlockingQueue<String> queue;

    public Brushes() {
        queue = new LinkedBlockingQueue(4);
        for(String type : new String[]{"regular", "triangular", "spectacular", "thin"}){
            queue.offer(type);
        }
    }

    public String takeBrush() throws InterruptedException {
        return queue.take();
    }

    public void returnBrush(String brush) {
        queue.offer(brush);
    }
}
