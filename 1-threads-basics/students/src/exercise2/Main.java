package exercise2;

public class Main {

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        for(int i=0;i<4;i++){
            threads[i] = new MyThread("Thread-"+i);
        }
        for(Thread thread : threads){
            thread.start();
        }
    }
}
