package javabasic.multithread.communication;

/**
 * @author lee
 * @since 2020-07-14
 * 1.线程A调用线程B的join方法，线程A需等待线程B执行完毕
 * 2.线程A调用线程B的join方法，若线程A被中断，线程A会抛出 InterruptedException 而结束
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadA());
        thread.start();
        thread.join();
        System.out.println("如果不加join方法，我会先被打出来，加了就不一样了");

        Thread threadA = new Thread(()->{
            System.out.println("睡 1s,看调用 join 的线程若被中断的情况");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        final Thread mainThread = Thread.currentThread();
        //中断主线程
        Thread threadB = new Thread(()-> mainThread.interrupt());
        threadB.start();

        try {
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("我是子线程，我先睡一秒");
                Thread.sleep(1000);
                System.out.println("我是子线程，我睡完了一秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
