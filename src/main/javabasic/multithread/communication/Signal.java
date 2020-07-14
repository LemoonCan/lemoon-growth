package main.javabasic.multithread.communication;

/**
 * @author lee
 * @date 2020-07-14
 */
public class Signal {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }

    /**
     * TODO 为何声明了volatile 变量没有更新到主内存或者B线程中的副本没有更新，最后B线程可能打印到了5
     */
    private static volatile int signal = 0;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("threadA: " + signal);
                    synchronized (this) {
                        signal++;
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("threadB：" + signal);
                    synchronized (this){
                        signal++;
                    }
                }
            }
        }
    }
}
