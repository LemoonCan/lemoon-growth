package main.javabasic.multithread.introduction;

/**
 * @author lee
 * @date 2020-07-13
 */
public class RunnableTest {
    public static void main(String[] args) {
        Thread myThread1 = new MyThread1();
        System.out.println(myThread1.getState());
        myThread1.start();
        System.out.println(myThread1.getState());

        Runnable myThread2 = new MyThread2();
        myThread2.run();

        //TODO 为何 Thread 有一个 Runnable 的构造方法？？
        Thread myThread3 = new Thread(() -> System.out.println("MyThread3"));
        myThread3.start();

        Thread testThread = new Thread(() -> {
            System.out.println("testThread当前线程组名字：" +
                    Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread线程名字：" +
                    Thread.currentThread().getName());
        });

        testThread.start();
        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());

    }

    public static class MyThread1 extends Thread{
        @Override
        public void run() {
            System.out.println("MyThread1");
        }
    }

    public static class MyThread2 implements Runnable{
        @Override
        public void run() {
            System.out.println("MyThread2");
        }
    }
}
