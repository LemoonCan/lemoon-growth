package main.javabasic.multithread.communication;

/**
 * @author lee
 * @date 2020-07-14
 *
 * TODO 为何无锁和有锁都能按顺序执行？？？
 */
public class ObjectLockTest {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(10);
        new Thread(new ThreadB()).start();
    }

    static class ThreadA implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread A " + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }
    }

}
