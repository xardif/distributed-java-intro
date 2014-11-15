package exercise4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ExecutorService() {
            @Override
            public void shutdown() {
                System.out.println("NO MORE COMMANDS");
            }

            @Override
            public List<Runnable> shutdownNow() {
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public boolean isTerminated() {
                return false;
            }

            @Override
            public boolean awaitTermination(long l, TimeUnit timeUnit) throws InterruptedException {
                return false;
            }

            @Override
            public <T> Future<T> submit(Callable<T> tCallable) {
                return null;
            }

            @Override
            public <T> Future<T> submit(Runnable runnable, T t) {
                return null;
            }

            @Override
            public Future<?> submit(Runnable runnable) {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables) throws InterruptedException {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit) throws InterruptedException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> callables) throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }

            @Override
            public void execute(Runnable runnable) {
                for(int i=0;i<4;i++){
                    runnable.run();
                }
            }
        };
        List<MyRunnable> runnables = new ArrayList<MyRunnable>();
        for(int i=0; i<4; i++){
            runnables.add(new MyRunnable());
            executorService.execute(runnables.get(i));
        }


        executorService.shutdown();
        executorService.awaitTermination(0, TimeUnit.SECONDS);
        System.out.println("FINISHED");

        ExecutorService es = Executors.newFixedThreadPool(4);
        for(int i=0; i<4; i++){
            es.execute(runnables.get(i));
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MILLISECONDS);

        System.out.println("!!FINISHED");
    }
}
