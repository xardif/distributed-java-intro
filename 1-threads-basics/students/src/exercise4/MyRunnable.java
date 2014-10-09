package exercise4;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10);
            //TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FINISHED");
    }
}
