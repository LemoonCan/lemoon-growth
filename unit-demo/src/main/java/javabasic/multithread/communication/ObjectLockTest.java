package javabasic.multithread.communication;

/**
 * @author lee
 * @since 2020-07-14
 *
 */
public class ObjectLockTest {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        Thread.sleep(10);

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

    /**
     * 关键字在实例方法上，锁为当前实例，临界区为整个方法
     */
    public synchronized void instanceLock() {
        // code
    }

    public void blockLockEqualsInstanceLock() {
        synchronized (this) {
            // code
        }
    }

    /**
     * 关键字在静态方法上，锁为当前Class对象，临界区为整个方法
     * Class对象和实例的区别？
     */
    public static synchronized void classLock() {
        // code
    }

    public void blockLockEqualsClassLock() {
        synchronized (this.getClass()) {
            // code
        }
    }

    /**
     * 关键字在代码块上，锁为括号里的对象，临界区为代码块内部区域
     */
    public void blockLock() {
        Object o = new Object();
        synchronized (o) {
            // code
        }
    }
}
