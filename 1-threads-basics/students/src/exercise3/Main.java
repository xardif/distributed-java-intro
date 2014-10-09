package exercise3;

import exercise2.MyThread;

public class Main {

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        for(int i=0;i<4;i++){
            threads[i] = new Thread(new MyRunnable(), "Thread-"+i);
        }
        for(Thread thread : threads){
            thread.start();
            /*try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        for(Thread thread : threads){
            do{
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while(thread.isAlive());
        }
        System.out.println("FINISHED!!!");
    }
}
